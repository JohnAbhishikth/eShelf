package com.lti.shelf.service;

import org.springframework.stereotype.Service;

import com.lti.shelf.entity.BookReview;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.repository.BookReviewRepository;
@Service
public class BookReviewServiceImpl implements BookReviewService
{
	BookReviewRepository bookReviewRepository;
	@Override
	public void addReview(BookReview bookReview) throws EShelfException {
		if(bookReview!=null) {
		try{
			//FIXME
			bookReviewRepository.save(bookReview);
		}catch(Exception e) {
			throw new EShelfException("Invalid Entry");
		}
		}
	}

	@Override
	public boolean updateReview(BookReview bookReview) throws EShelfException {

		if(bookReview!=null) {
			try {
				bookReviewRepository.save(bookReview);
			}catch(Exception e) {
				throw new EShelfException("Invalid Entry");
			}
		}
		return true;
	}

	@Override
	public boolean deletereview(BookReview bookReview) {
		bookReviewRepository.delete(bookReview);
		return true;
	}

}
