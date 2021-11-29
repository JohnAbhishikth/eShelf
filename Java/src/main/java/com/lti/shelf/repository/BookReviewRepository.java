package com.lti.shelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.shelf.entity.BookReview;
import com.lti.shelf.entity.BookReviewPK;

public interface BookReviewRepository extends JpaRepository<BookReview, BookReviewPK> {

}
