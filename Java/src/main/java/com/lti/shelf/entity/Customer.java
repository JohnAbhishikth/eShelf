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
 * The persistent class for the customer_login database table.
 * 
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customer_login")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private String userId;

	private String email;

	private String name;

	private String password;

	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToMany(mappedBy = "customerLogin")
	private List<Address> addressTbls;

	@OneToMany(mappedBy = "customerLogin")
	private List<BookReview> bookReviews;

	@OneToMany(mappedBy = "customerLogin")
	private List<OrderDetail> orderDetails;

	@OneToMany(mappedBy = "customerLogin")
	private List<ShoppingCartItem> shoppingCartItems;

	/**
	 * @param userId
	 * @param email
	 * @param name
	 * @param password
	 * @param phoneNumber
	 */
	public Customer(String userId, String email, String name, String password, String phoneNumber) {
		this.userId = userId;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, name, password, phoneNumber, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(userId, other.userId);
	}

}