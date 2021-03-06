package com.lti.shelf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.shelf.dto.BookDTO;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.service.BookService;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("/add")
	@ResponseBody
	public String addBook(@RequestBody BookDTO bookDTO) {
		try {
			bookService.addBook(bookDTO);
		} catch (EShelfException e) {
			return e.getMessage();
		}
		return "Book Added Successfully!!!";
	}

	@PostMapping("/update")
	@ResponseBody
	public String updateBook(@RequestBody BookDTO bookDTO) {
		try {
			bookService.updateBook(bookDTO);
		} catch (EShelfException e) {
			return e.getMessage();
		}
		return "Book Updated!!!";
	}

	@GetMapping("/getall")
	public List<BookDTO> getAllBooks() throws EShelfException {
		return bookService.getAllBooks();
	}

	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public BookDTO getBookById(@PathVariable String id) throws EShelfException {
		return bookService.searchBookById(id);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteBook(@PathVariable String id) throws EShelfException {
		try {
			bookService.deleteBook(id);
			return "Book Deleted Successfully!!!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping("/getbyauthor/{author}")
	@ResponseBody
	public List<BookDTO> searchBookByAuthor(@PathVariable String author) throws EShelfException {
		return bookService.searchBookByAuthor(author);
	}

	@GetMapping("/getbycategory/{category}")
	@ResponseBody
	public List<BookDTO> searchBookByCategory(@PathVariable String category) throws EShelfException {
		return bookService.searchBookByCategory(category);
	}
}
