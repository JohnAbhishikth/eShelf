package com.lti.shelf.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the books database table.
 * 
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "books")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "inventory_id")
	private String inventoryId;

	@Column(name = "author_name")
	private String authorName;

	@Column(name = "book_count")
	private int bookCount;

	@Column(name = "book_name")
	private String bookName;

	@Column(name = "category")
	private String bookCategory;

	private double price;

	@OneToMany(mappedBy = "book")
	private List<BookReview> bookReviews;

	@OneToMany(mappedBy = "book")
	private List<PurchaseHistory> purchaseHistories;

	@OneToMany(mappedBy = "book")
	private List<ShoppingCartItem> shoppingCartItems;

	/**
	 * @param inventoryId
	 * @param authorName
	 * @param bookCount
	 * @param bookName
	 * @param price
	 */
	public Book(String inventoryId, String authorName, String bookCategory, int bookCount, String bookName,
			double price) {
		this.bookCategory = bookCategory;
		this.inventoryId = inventoryId;
		this.authorName = authorName;
		this.bookCount = bookCount;
		this.bookName = bookName;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [inventoryId=" + inventoryId + ", authorName=" + authorName + ", bookCount=" + bookCount
				+ ", bookName=" + bookName + ", bookCategory=" + bookCategory + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorName, bookCount, bookName, inventoryId, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Book))
			return false;
		Book other = (Book) obj;
		return Objects.equals(authorName, other.authorName) && bookCount == other.bookCount
				&& Objects.equals(bookName, other.bookName) && Objects.equals(inventoryId, other.inventoryId)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}
}