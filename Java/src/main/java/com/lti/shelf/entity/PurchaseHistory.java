package com.lti.shelf.entity;

import java.io.Serializable;
import java.util.List;

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
	@Column(name = "purchase_id")
	private String purchaseId;

	private double price;

	private int quantity;

	// bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy = "purchaseHistory")
	private List<OrderDetail> orderDetails;

	// bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Book book;

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setPurchaseHistory(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setPurchaseHistory(null);

		return orderDetail;
	}

	/**
	 * @param purchaseId
	 * @param price
	 * @param quantity
	 */
	public PurchaseHistory(String purchaseId, double price, int quantity) {
		this.purchaseId = purchaseId;
		this.price = price;
		this.quantity = quantity;
	}

}