package com.lti.shelf.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The persistent class for the customer_login database table.
 * 
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customer_login")
public class CustomerLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private String userId;

	private String email;

	private String name;

	private String password;

	@Column(name = "phone_number")
	private String phoneNumber;

	// bi-directional many-to-one association to AddressTbl
	@OneToMany(mappedBy = "customerLogin")
	private List<AddressTbl> addressTbls;

	// bi-directional many-to-one association to BookReview
	@OneToMany(mappedBy = "customerLogin")
	private List<BookReview> bookReviews;

	// bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy = "customerLogin")
	private List<OrderDetail> orderDetails;

	// bi-directional many-to-one association to ShoppingCartItem
	@OneToMany(mappedBy = "customerLogin")
	private List<ShoppingCartItem> shoppingCartItems;

	public AddressTbl addAddressTbl(AddressTbl addressTbl) {
		getAddressTbls().add(addressTbl);
		addressTbl.setCustomerLogin(this);

		return addressTbl;
	}

	public AddressTbl removeAddressTbl(AddressTbl addressTbl) {
		getAddressTbls().remove(addressTbl);
		addressTbl.setCustomerLogin(null);

		return addressTbl;
	}

	public BookReview addBookReview(BookReview bookReview) {
		getBookReviews().add(bookReview);
		bookReview.setCustomerLogin(this);

		return bookReview;
	}

	public BookReview removeBookReview(BookReview bookReview) {
		getBookReviews().remove(bookReview);
		bookReview.setCustomerLogin(null);

		return bookReview;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setCustomerLogin(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setCustomerLogin(null);

		return orderDetail;
	}

	public ShoppingCartItem addShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		getShoppingCartItems().add(shoppingCartItem);
		shoppingCartItem.setCustomerLogin(this);

		return shoppingCartItem;
	}

	public ShoppingCartItem removeShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		getShoppingCartItems().remove(shoppingCartItem);
		shoppingCartItem.setCustomerLogin(null);

		return shoppingCartItem;
	}

	/**
	 * @param userId
	 * @param email
	 * @param name
	 * @param password
	 * @param phoneNumber
	 */
	public CustomerLogin(String userId, String email, String name, String password, String phoneNumber) {
		this.userId = userId;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

}