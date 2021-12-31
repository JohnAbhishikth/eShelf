package com.lti.shelf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.lti.shelf.dto.BookDTO;
import com.lti.shelf.entity.Book;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;

	/*
	@Override
	public void addBook(Book book) throws EShelfException {
		if (book != null) {
			try {
				bookRepository.save(book);
			} catch (Exception e) {
				throw new EShelfException("Invalid entry");
			}
		} else {
			System.out.println("Enter all the fields");
		}
	}

	@Override
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	@Override
	public boolean updateBook(Book book) throws EShelfException {
		if (book != null) {
			try {
				bookRepository.save(book);
				return true;
			} catch (Exception e) {
				throw new EShelfException("Invalid entry");
			}
		} else {
			System.out.println("Enter all the fields");
			return false;
		}

	}

	@Override
	public boolean deleteBook(Book book) {
		bookRepository.delete(book);
		return true;
	}

	@Override
	public Book searchBookId(String inventory_id) {
		return bookRepository.getById(inventory_id);
	}
*/
	@Override
	public void addBook(BookDTO bookDto) throws EShelfException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookDTO> getAllBooks() throws EShelfException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBook(BookDTO bookDto) throws EShelfException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBook(String inventoryId) throws EShelfException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BookDTO searchBookById(String inventoryId) throws EShelfException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> searchBookByAuthor(String author) throws EShelfException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> searchBookByCategory(String category) throws EShelfException {
		// TODO Auto-generated method stub
		return null;
	}

}

//CODE QUALITY
//CODE COVERAGE
