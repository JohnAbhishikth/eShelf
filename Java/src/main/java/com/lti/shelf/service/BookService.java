package com.lti.shelf.service;

import java.util.List;

import com.lti.shelf.dto.BookDTO;
import com.lti.shelf.exception.EShelfException;

public interface BookService {
	void addBook(BookDTO bookDto) throws EShelfException;
	List<BookDTO> getAllBooks() throws EShelfException;
	boolean updateBook(BookDTO bookDto) throws EShelfException;
	boolean deleteBook(String inventoryId) throws EShelfException;
	BookDTO searchBookById(String inventoryId) throws EShelfException;
	List<BookDTO> searchBookByAuthor(String author) throws EShelfException;
	List<BookDTO> searchBookByCategory(String category) throws EShelfException;
}
