package com.lti.shelf.service;

import java.util.List;

import com.lti.shelf.dto.BookReviewDTO;
import com.lti.shelf.exception.EShelfException;

interface BookReviewService {
	void addReview(BookReviewDTO bookReviewDto) throws EShelfException;
	boolean updateReview(BookReviewDTO bookReviewDto) throws EShelfException;
	boolean deletereview(String userId, String inventoryId) throws EShelfException;
	List<BookReviewDTO> getAllBookReviewsByBookId(String inventoryId) throws EShelfException;
	List<BookReviewDTO> getAllBookReviewsByUserId(String userId) throws EShelfException;
}
