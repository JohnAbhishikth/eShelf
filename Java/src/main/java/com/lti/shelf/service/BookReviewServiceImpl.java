package com.lti.shelf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lti.shelf.dto.BookReviewDTO;
import com.lti.shelf.entity.Book;
import com.lti.shelf.entity.BookReview;
import com.lti.shelf.entity.BookReviewPK;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.repository.BookReviewRepository;
@Service
public class BookReviewServiceImpl implements BookReviewService
{
	BookReviewRepository bookReviewRepository;
	
		@Override
	public void addReview(BookReviewDTO bookReviewDto) throws EShelfException {
			try {
				if(bookReviewDto == null) {
					throw new EShelfException("Empty Review");
				}
				BookReviewPK bookPK = new BookReviewPK(bookReviewDto.getInventoryId(), bookReviewDto.getUserId());
				BookReview bookReview = new BookReview(bookPK, bookReviewDto.getRating(), bookReviewDto.getReviews());
				bookReviewRepository.save(bookReview);
			}catch (Exception e) {
				throw new EShelfException("Unable to add Review");
			}
	}

	@Override
	public boolean updateReview(BookReviewDTO bookReviewDto) throws EShelfException {
		return false;
	}

	@Override
	public boolean deletereview(String userId, String inventoryId) throws EShelfException {
		try {
			BookReviewPK bookReviewPK = new BookReviewPK(userId, inventoryId);
			bookReviewRepository.deleteById(bookReviewPK);
			return true;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<BookReviewDTO> getAllBookReviewsByBookId(String inventoryId) throws EShelfException {
		if(inventoryId!=null) {
			List<BookReview> findAllById = bookReviewRepository.findAllByBookId(inventoryId);
			List<BookReviewDTO> bookDtoList = new ArrayList<>();
			try {
				for(BookReview bookReview:findAllById) {
					BookReviewDTO bookReviewDto = new BookReviewDTO();
					bookReviewDto.setInventoryId(inventoryId);
					bookReviewDto.setRating(bookReview.getRating());
					bookReviewDto.setReviews(bookReview.getReviews());
					bookReviewDto.setUserId(bookReview.getCustomerLogin().getUserId());
					bookDtoList.add(bookReviewDto);
				}
			}catch (Exception e) {
				throw new EShelfException("No Review Present");
			}
			return bookDtoList;
		}
		else {
			throw new EShelfException("No Review Present");
		}
	}

	@Override
	public List<BookReviewDTO> getAllBookReviewsByUserId(String userId) throws EShelfException {
		if(userId!=null) {
			List<BookReview> findAllById = bookReviewRepository.findAllByUserId(userId);
			List<BookReviewDTO> bookDtoList = new ArrayList<>();
			try {
				for(BookReview bookReview:findAllById) {
					BookReviewDTO bookReviewDto = new BookReviewDTO();
					bookReviewDto.setInventoryId(bookReview.getBook().getInventoryId());
					bookReviewDto.setRating(bookReview.getRating());
					bookReviewDto.setReviews(bookReview.getReviews());
					bookReviewDto.setUserId(userId);
					bookDtoList.add(bookReviewDto);
				}
			}catch (Exception e) {
				throw new EShelfException("No Review Present");
			}
			return bookDtoList;
		}
		else {
			throw new EShelfException("No Review Present");
		}
	}

}
