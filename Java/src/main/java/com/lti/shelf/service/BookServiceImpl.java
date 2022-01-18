package com.lti.shelf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.shelf.dto.BookDTO;
import com.lti.shelf.entity.Book;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;

	@Override
	public void addBook(BookDTO bookDto) throws EShelfException {
		if (bookDto != null && bookDto.getInventoryId() != null) {
			Optional<Book> findById = bookRepository.findById(bookDto.getInventoryId());
			if (findById.isPresent())
				throw new EShelfException("Book already Available");
			Book book = new Book(bookDto.getInventoryId(), bookDto.getAuthorName(), bookDto.getBookCategory(),
					bookDto.getBookCount(), bookDto.getBookName(), bookDto.getPrice());
			try {
				bookRepository.save(book);
			} catch (Exception e) {
				throw new EShelfException("Unable to Save Book");
			}
		} else {
			System.out.println("Book Object can't be null");
		}
	}

	@Override
	public List<BookDTO> getAllBooks() throws EShelfException {

		try {
			List<Book> bookList = bookRepository.findAll();
			if (bookList.isEmpty())
				throw new EShelfException("No Books Available");
			List<BookDTO> bookDtoList = new ArrayList<>();

			for (Book book : bookList) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setInventoryId(book.getInventoryId());
				bookDTO.setAuthorName(book.getAuthorName());
				bookDTO.setBookName(book.getBookName());
				bookDTO.setPrice(book.getPrice());
				bookDTO.setBookCategory(book.getBookCategory());
				bookDTO.setBookCount(book.getBookCount());
				bookDtoList.add(bookDTO);
			}

			return bookDtoList;
		} catch (Exception e) {
			throw e;
		}
	}

// Update Not Finished
	@Override
	public boolean updateBook(BookDTO bookDto) throws EShelfException {
		if (bookDto != null) {
			Optional<Book> findById = bookRepository.findById(bookDto.getInventoryId());
			if (findById.isPresent()) {
				Book book = new Book();
				try {
					book.setAuthorName(bookDto.getAuthorName());
					book.setBookName(bookDto.getBookName());
					book.setBookCategory(bookDto.getBookCategory());
					book.setBookCount(bookDto.getBookCount());
					book.setPrice(bookDto.getPrice());
					book.setInventoryId(bookDto.getInventoryId());
					bookRepository.save(book);
				} catch (Exception e) {
					throw new EShelfException("Unable to Save Book");
				}
			} else {
				throw new EShelfException("Book ID not present");
			}
			return true;
		} else {
			throw new EShelfException("Book Object can't be null");
		}
	}

	@Override
	public boolean deleteBook(String inventoryId) throws EShelfException {
		if (inventoryId != null) {
			Optional<Book> findById = bookRepository.findById(inventoryId);
			if (!findById.isPresent())
				throw new EShelfException("Cannot find the Book ID");
			bookRepository.deleteById(inventoryId);
			return true;
		} else {
			throw new EShelfException("Book ID cannot be null");
		}
	}

	@Override
	public BookDTO searchBookById(String inventoryId) throws EShelfException {
		if (inventoryId != null) {
			Optional<Book> findById = bookRepository.findById(inventoryId);
			if (!findById.isPresent())
				throw new EShelfException("Cannot find the Book ID");
			Book book = findById.get();
			BookDTO bookDto = new BookDTO();
			bookDto.setInventoryId(inventoryId);
			bookDto.setBookName(book.getAuthorName());
			bookDto.setAuthorName(book.getAuthorName());
			bookDto.setBookCount(book.getBookCount());
			bookDto.setPrice(book.getPrice());
			bookDto.setBookCategory(book.getBookCategory());
			return bookDto;
		} else {
			throw new EShelfException("Book ID cannot be null");
		}
	}

	@Override
	public List<BookDTO> searchBookByAuthor(String author) throws EShelfException {
		if (author != null) {
			List<Book> searchBookByAuthor = bookRepository.findByAuthor(author);
			List<BookDTO> bookDtoList = new ArrayList<>();
			for (Book book : searchBookByAuthor) {
				BookDTO bookDto = new BookDTO();
				bookDto.setInventoryId(book.getInventoryId());
				bookDto.setBookName(book.getBookName());
				bookDto.setAuthorName(book.getAuthorName());
				bookDto.setBookCount(book.getBookCount());
				bookDto.setPrice(book.getPrice());
				bookDto.setBookCategory(book.getBookCategory());
				bookDtoList.add(bookDto);
			}
			return bookDtoList;
		} else {
			throw new EShelfException("Author cannot be null");
		}
	}

	@Override
	public List<BookDTO> searchBookByCategory(String category) throws EShelfException {
		if (category != null) {
			List<Book> searchBookByCategory = bookRepository.findByCategory(category);
			List<BookDTO> bookDtoList = new ArrayList<>();
			for (Book book : searchBookByCategory) {
				BookDTO bookDto = new BookDTO();
				bookDto.setInventoryId(book.getInventoryId());
				bookDto.setBookName(book.getBookName());
				bookDto.setAuthorName(book.getAuthorName());
				bookDto.setBookCount(book.getBookCount());
				bookDto.setPrice(book.getPrice());
				bookDto.setBookCategory(book.getBookCategory());
				bookDtoList.add(bookDto);
			}
			return bookDtoList;
		} else {
			throw new EShelfException("No Category Present");
		}
	}
}
