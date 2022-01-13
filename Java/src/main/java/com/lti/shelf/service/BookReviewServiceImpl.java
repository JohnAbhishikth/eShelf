package com.lti.shelf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lti.shelf.dto.BookReviewDTO;
import com.lti.shelf.dto.ShoppingCartDTO;
import com.lti.shelf.entity.Book;
import com.lti.shelf.entity.BookReview;
import com.lti.shelf.entity.BookReviewPK;
import com.lti.shelf.entity.ShoppingCartItem;
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
		/*			if (inventoryId == null || inventoryId.length() == 0) {
			throw new EShelfException("Enter Valid Book Id");
		}
		List<BookReview> allBookReviewByBookId = bookReviewRepository
				.findAllByBookId(inventoryId);
		System.out.println(id);
		BookReview bookReview = new BookReview();
		System.out.println(bookReview.getRating()+" "+bookReview.getReviews()+" "+bookReview.getCustomerLogin().getUserId());
		List<BookReview> allBookReviewByBookId = bookReviewRepository.findAllByBookId(inventoryId);
		if (allBookReviewByBookId.isEmpty())
			throw new EShelfException("No details");
		
		List<BookReviewDTO> bookReviewDTOList = new ArrayList<>();
		System.out.println(id);
			for (BookReview bookReview1 : allBookReviewByBookId) {
				BookReviewDTO bookReviewDto = new BookReviewDTO();
				bookReviewDto.setInventoryId(inventoryId);
				bookReviewDto.setRating(bookReview.getRating());
				bookReviewDto.setReviews(bookReview.getReviews());
				bookReviewDto.setUserId(bookReview.getCustomerLogin().getUserId());
				bookReviewDTOList.add(bookReviewDto);
			}
			return bookReviewDTOList;
*/	return null;}
	

	@Override
	public List<BookReviewDTO> getAllBookReviewsByUserId(String userId) throws EShelfException {
		System.out.println("2 userId :" + userId);
		if (userId == null || userId.length() == 0) {
			throw new EShelfException("Enter Valid User Id");
		}

		List<BookReview> allBookReviewByUserId = bookReviewRepository
				.getAllUserReviews(userId);
		System.out.println("3");
		if (allBookReviewByUserId.isEmpty())
			throw new EShelfException("No details");

		List<BookReviewDTO> bookReviewDTOList = new ArrayList<>();
		try {
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

//	@Override
//	public List<BookReviewDTO> getAllBookReviewsByBookId(String inventoryId) throws EShelfException {
//			List<BookReview> allBookReview = bookReviewRepository.findAllByBookId(inventoryId);
//			System.out.println("hu");
//			List<BookReviewDTO> bkList = new ArrayList();
//			BookReview bookReview = new BookReview();
//			BookReviewDTO bookReviewDto = new BookReviewDTO();
//			bookReviewDto.setInventoryId(inventoryId);
//			bookReviewDto.setRating(bookReview.getRating());
//			bookReviewDto.setReviews(bookReview.getReviews());
//			bookReviewDto.setUserId(bookReview.getCustomerLogin().getUserId());
//			bkList.add(bookReviewDto);
//			return bkList;
//	}

}
