package com.lti.shelf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lti.shelf.entity.BookReview;
import com.lti.shelf.entity.BookReviewPK;

public interface BookReviewRepository extends JpaRepository<BookReview, BookReviewPK> {

	@Query(value = "select * from book_review where inventory_id=?1", nativeQuery = true)
	List<BookReview> findAllByBookId(String inventoryId);

	@Query(value = "select * from BOOK_REVIEW where user_Id=?1", nativeQuery = true)
	List<BookReview> getAllReviewsUserId(String userId);

}
