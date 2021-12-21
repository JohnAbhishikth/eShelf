package com.lti.shelf.service;

import org.springframework.stereotype.Service;

import com.lti.shelf.entity.BookReview;
import com.lti.shelf.exceptions.EShelfException;
import com.lti.shelf.repository.BookReviewRepository;
@Service
public class BookReviewServiceImpl implements BookReviewService
{
	BookReviewRepository bookReviewRepository;
	@Override
	public void addReview(BookReview bookReview) throws EShelfException {
		// TODO Auto-generated method stub
		if(bookReview!=null) {
		try{
			bookReviewRepository.save(bookReview);
		}catch(Exception e) {
			throw new EShelfException("Invalid Entry");
		}
		}
	}

	@Override
	public boolean updateReview(BookReview bookReview) throws EShelfException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		bookReviewRepository.delete(bookReview);
		return true;
	}

}
