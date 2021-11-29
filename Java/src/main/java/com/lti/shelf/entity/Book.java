package com.lti.shelf.entity;

import java.io.Serializable;
import java.util.List;

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

	private double price;

	// bi-directional many-to-one association to BookReview
	@OneToMany(mappedBy = "book")
	private List<BookReview> bookReviews;

	// bi-directional many-to-one association to PurchaseHistory
	@OneToMany(mappedBy = "book")
	private List<PurchaseHistory> purchaseHistories;

	// bi-directional many-to-one association to ShoppingCartItem
	@OneToMany(mappedBy = "book")
	private List<ShoppingCartItem> shoppingCartItems;

	public BookReview addBookReview(BookReview bookReview) {
		getBookReviews().add(bookReview);
		bookReview.setBook(this);

		return bookReview;
	}

	public BookReview removeBookReview(BookReview bookReview) {
		getBookReviews().remove(bookReview);
		bookReview.setBook(null);

		return bookReview;
	}

	public PurchaseHistory addPurchaseHistory(PurchaseHistory purchaseHistory) {
		getPurchaseHistories().add(purchaseHistory);
		purchaseHistory.setBook(this);

		return purchaseHistory;
	}

	public PurchaseHistory removePurchaseHistory(PurchaseHistory purchaseHistory) {
		getPurchaseHistories().remove(purchaseHistory);
		purchaseHistory.setBook(null);

		return purchaseHistory;
	}

	public ShoppingCartItem addShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		getShoppingCartItems().add(shoppingCartItem);
		shoppingCartItem.setBook(this);

		return shoppingCartItem;
	}

	public ShoppingCartItem removeShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		getShoppingCartItems().remove(shoppingCartItem);
		shoppingCartItem.setBook(null);

		return shoppingCartItem;
	}

	/**
	 * @param inventoryId
	 * @param authorName
	 * @param bookCount
	 * @param bookName
	 * @param price
	 */
	public Book(String inventoryId, String authorName, int bookCount, String bookName, double price) {
		this.inventoryId = inventoryId;
		this.authorName = authorName;
		this.bookCount = bookCount;
		this.bookName = bookName;
		this.price = price;
	}
}