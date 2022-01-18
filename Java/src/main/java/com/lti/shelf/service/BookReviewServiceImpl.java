package com.lti.shelf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.shelf.dto.BookReviewDTO;
import com.lti.shelf.entity.Address;
import com.lti.shelf.entity.Book;
import com.lti.shelf.entity.BookReview;
import com.lti.shelf.entity.BookReviewPK;
import com.lti.shelf.entity.Customer;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.repository.BookRepository;
import com.lti.shelf.repository.BookReviewRepository;
import com.lti.shelf.repository.CustomerRepository;

@Service
public class BookReviewServiceImpl implements BookReviewService {

	@Autowired
	BookReviewRepository bookReviewRepository;

	@Override
	public void addReview(BookReviewDTO bookReviewDto) throws EShelfException {
		try {
			if (bookReviewDto == null) {
				throw new EShelfException("Empty Review");
			}
			BookReviewPK bookPK = new BookReviewPK(bookReviewDto.getInventoryId(), bookReviewDto.getUserId());
			BookReview bookReview = new BookReview(bookPK, bookReviewDto.getRating(), bookReviewDto.getReviews());
			bookReviewRepository.save(bookReview);
		} catch (Exception e) {
			throw new EShelfException("Unable to add Review");
		}
	}

	@Override
	public boolean updateReview(BookReviewDTO bookReviewDto) throws EShelfException {
		if (bookReviewDto != null) {
			try {
				BookReviewPK bookReviewpk = new BookReviewPK(bookReviewDto.getInventoryId(), bookReviewDto.getUserId());
				Optional<BookReview> bookReviewOptional = bookReviewRepository.findById(bookReviewpk);
				if (!bookReviewOptional.isPresent()) {
					throw new EShelfException("Book Review is not Present");
				} else {
					BookReview bookReview = bookReviewOptional.get();
					bookReview.setId(bookReviewpk);
					bookReview.setRating(bookReviewDto.getRating());
					bookReview.setReviews(bookReviewDto.getReviews());
					bookReviewRepository.save(bookReview);
				}
			} catch (Exception e) {
				throw new EShelfException("Invalid Book Review");
			}
		} else {
			throw new EShelfException("Empty Review");
		}
		return true;
	}

	@Override
	public boolean deletereview(String userId, String inventoryId) throws EShelfException {
		if (inventoryId != null && userId != null) {
			try {
				BookReviewPK bookReviewpk = new BookReviewPK(inventoryId, userId);
				Optional<BookReview> bookReviewOptional = bookReviewRepository.findById(bookReviewpk);
				if (!bookReviewOptional.isPresent()) {
					throw new EShelfException("Book Review is not Present");
				}
				bookReviewRepository.deleteById(bookReviewpk);
			} catch (Exception e) {
				throw new EShelfException("Invalid details");
			}
		} else {
			throw new EShelfException("BookID or UserID cannot be Null");
		}
		return true;
	}

	@Override
	public List<BookReviewDTO> getAllBookReviewsByBookId(String inventoryId) throws EShelfException {
		if (inventoryId == null || inventoryId.length() == 0) {
			throw new EShelfException("Enter Valid Book Id");
		}
		try {
			List<BookReview> allBookReviewByBookId = bookReviewRepository.findAllByBookId(inventoryId);
			if (allBookReviewByBookId.isEmpty())
				throw new EShelfException("No details");
			List<BookReviewDTO> bookReviewDTOList = new ArrayList<>();
			for (BookReview bookReview : allBookReviewByBookId) {
				BookReviewDTO bookReviewDto = new BookReviewDTO();
				bookReviewDto.setInventoryId(inventoryId);
				bookReviewDto.setRating(bookReview.getRating());
				bookReviewDto.setReviews(bookReview.getReviews());
				bookReviewDto.setUserId(bookReview.getCustomerLogin().getUserId());
				bookReviewDTOList.add(bookReviewDto);
			}
			return bookReviewDTOList;
		} catch (Exception e) {
			throw new EShelfException(e.getMessage());
		}
	}

	@Override
	public List<BookReviewDTO> getAllBookReviewsByUserId(String userId) throws EShelfException {
		if (userId == null || userId.length() == 0) {
			throw new EShelfException("Enter Valid User Id");
		}
		try {
			List<BookReview> allBookReviewByUserId = bookReviewRepository.getAllReviewsUserId(userId);
			if (allBookReviewByUserId.isEmpty())
				throw new EShelfException("No details");
			List<BookReviewDTO> bookReviewDTOList = new ArrayList<>();
			for (BookReview bookReview : allBookReviewByUserId) {
				BookReviewDTO bookReviewDto = new BookReviewDTO();
				bookReviewDto.setInventoryId(bookReview.getBook().getInventoryId());
				bookReviewDto.setRating(bookReview.getRating());
				bookReviewDto.setReviews(bookReview.getReviews());
				bookReviewDto.setUserId(userId);
				bookReviewDTOList.add(bookReviewDto);
			}
			return bookReviewDTOList;
		} catch (Exception e) {
			throw new EShelfException(e.getMessage());
		}
	}

}
