package com.lti.shelf.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

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
	private Timestamp reviewDate = new Timestamp(System.currentTimeMillis());

	private String reviews;

	@ManyToOne
	@JoinColumn(name = "inventory_id", insertable = false, updatable = false)
	private Book book;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private Customer customerLogin;

	/**
	 * @param id
	 * @param rating
	 * @param reviewDate
	 * @param reviews
	 * @param book
	 * @param customerLogin
	 */
	public BookReview(BookReviewPK id, int rating, String reviews) {
		this.id = id;
		this.rating = rating;
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "BookReview [id=" + id + ", rating=" + rating + ", reviews=" + reviews + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, rating, reviewDate, reviews);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BookReview))
			return false;
		BookReview other = (BookReview) obj;
		return Objects.equals(id, other.id) && rating == other.rating && Objects.equals(reviewDate, other.reviewDate)
				&& Objects.equals(reviews, other.reviews);
	}

}