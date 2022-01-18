package com.lti.shelf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lti.shelf.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {
	
	@Query(value = "SELECT * FROM BOOKS b WHERE b.author_name like %:author%", nativeQuery = true)
	List<Book> findByAuthor(String author);
	
	@Query(value = "SELECT * FROM BOOKS b WHERE b.category like %:category%", nativeQuery = true)
	List<Book> findByCategory(String category);
	
}
