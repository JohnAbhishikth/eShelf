package com.lti.shelf.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the purchase_history database table.
 * 
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "purchase_history")
public class PurchaseHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "purchase_id", insertable = false, updatable = false)
	private String purchaseId;

	private double price;

	private int quantity;

	@OneToMany(mappedBy = "purchaseHistory")
	private List<OrderDetail> orderDetails;

	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Book book;

	/**
	 * @param purchaseId
	 * @param price
	 * @param quantity
	 */
	public PurchaseHistory(String purchaseId, double price, int quantity, Book book) {
		this.purchaseId = purchaseId;
		this.price = price;
		this.quantity = quantity;
		this.book = book;
	}

	@Override
	public String toString() {
		return "PurchaseHistory [purchaseId=" + purchaseId + ", price=" + price + ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(price, purchaseId, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PurchaseHistory))
			return false;
		PurchaseHistory other = (PurchaseHistory) obj;
		return Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(purchaseId, other.purchaseId) && quantity == other.quantity;
	}

}