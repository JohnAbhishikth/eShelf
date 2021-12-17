package com.lti.shelf.service;

import java.util.List;

import com.lti.shelf.entity.Book;
import com.lti.shelf.exceptions.EShelfException;

public interface BookService {
	public void addBook(Book book) throws EShelfException;
	public List<Book>getBooks();
	public boolean updateBook(Book book) throws EShelfException;
	public boolean deleteBook(Book book);
	public Book searchBookId(String inventory_id);
}
