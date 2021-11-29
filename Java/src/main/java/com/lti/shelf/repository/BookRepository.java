package com.lti.shelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.shelf.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
