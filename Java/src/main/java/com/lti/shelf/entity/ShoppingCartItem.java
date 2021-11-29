package com.lti.shelf.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the shopping_cart_items database table.
 * 
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "shopping_cart_items")
public class ShoppingCartItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ShoppingCartItemPK id;

	private Timestamp date;

	private double price;

	private int quantity;

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
	 * @param date
	 * @param price
	 * @param quantity
	 */
	public ShoppingCartItem(ShoppingCartItemPK id, Timestamp date, double price, int quantity) {
		this.id = id;
		this.date = date;
		this.price = price;
		this.quantity = quantity;
	}

}