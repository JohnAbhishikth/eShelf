package com.lti.shelf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.shelf.dto.BookReviewDTO;
import com.lti.shelf.dto.ShoppingCartDTO;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.service.BookReviewService;

/*
 * add review 
 * update review 
 * delete review
 * show review
 */

@RestController
@CrossOrigin
@RequestMapping("/bookreview")
public class BookReviewController {

	@Autowired
	BookReviewService bookreviewService;

	@PostMapping("/add")
	@ResponseBody
	public String addReview(@RequestBody BookReviewDTO bookReviewDTO) throws EShelfException {
		bookreviewService.addReview(bookReviewDTO);
		return "Book Review Added!!!";
	}

	@PostMapping("/update")
	@ResponseBody
	public String updateReview(@RequestBody BookReviewDTO bookReviewDTO) {
		return null;
	}

	@GetMapping("/bookid/{inventoryId}")
	public List<BookReviewDTO> getAllReviews(@PathVariable String inventoryId) throws EShelfException {

		return bookreviewService.getAllBookReviewsByBookId(inventoryId);
	}

	@GetMapping("/userid/{userId}")
	public List<BookReviewDTO> showReview(@PathVariable String userId) throws EShelfException {
		System.out.println("1 userId :" + userId);
		return bookreviewService.getAllBookReviewsByUserId(userId);
	}

	@GetMapping("/delete")
	@ResponseBody
	public String deleteReview(@RequestBody BookReviewDTO bookReviewDTO) throws EShelfException {
		return null;
	}
}
