package com.lti.shelf.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the book_review database table.
 * 
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "book_review")
public class BookReview implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BookReviewPK id;

	private int rating;

	@Column(name = "review_date")
	private Timestamp reviewDate;

	private String reviews;

	// bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Book book;

	// bi-directional many-to-one association to CustomerLogin
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customerLogin;

	/**
	 * @param id
	 * @param rating
	 * @param reviewDate
	 * @param reviews
	 * @param book
	 * @param customerLogin
	 */
	public BookReview(BookReviewPK id, int rating, Timestamp reviewDate, String reviews, Book book,
			Customer customerLogin) {
		this.id = id;
		this.rating = rating;
		this.reviewDate = reviewDate;
		this.reviews = reviews;
		this.book = book;
		this.customerLogin = customerLogin;
	}

}