package com.lti.shelf.service;

import java.util.List;

import com.lti.shelf.entity.BookReview;
import com.lti.shelf.exceptions.EShelfException;

public interface BookReviewService {
	public void addReview(BookReview bookReview) throws EShelfException;
	public boolean updateReview(BookReview bookReview) throws EShelfException;
	public boolean deletereview(BookReview bookReview);
}
