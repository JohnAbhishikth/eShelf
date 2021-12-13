package com.lti.shelf.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

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

@Table(name = "shopping_cart_items")
public class ShoppingCartItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@EmbeddedId
	private ShoppingCartItemPK id;

	@Getter
	private Timestamp date = new Timestamp(System.currentTimeMillis());

	@Getter
	@Setter
	private double price;

	@Getter
	@Setter
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "inventory_id", insertable = false, updatable = false)
	private Book book;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private Customer customerLogin;

	/**
	 * @param id
	 * @param date
	 * @param price
	 * @param quantity
	 */
	public ShoppingCartItem(ShoppingCartItemPK id, double price, int quantity) {
		this.id = id;
		this.price = price;
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, price, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ShoppingCartItem))
			return false;
		ShoppingCartItem other = (ShoppingCartItem) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && quantity == other.quantity;
	}

}