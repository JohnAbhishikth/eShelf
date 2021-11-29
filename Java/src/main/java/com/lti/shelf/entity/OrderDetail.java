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
 * The persistent class for the order_details database table.
 * 
 */
@Entity
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@EmbeddedId
	private OrderDetailPK id;

	@Getter
	@Column(name = "date_of_purchase", updatable = false)
	private Timestamp dateOfPurchase = new Timestamp(System.currentTimeMillis());

	@Getter
	@Setter
	@Column(name = "day_of_delivery")
	private Timestamp dayOfDelivery;

	// bi-directional many-to-one association to AddressTbl
	@Setter
	@Getter
	@ManyToOne
	@JoinColumn(name = "address_id")
	private AddressTbl addressTbl;

	// bi-directional many-to-one association to CustomerLogin
	@Setter
	@Getter
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customerLogin;

	// bi-directional many-to-one association to PurchaseHistory
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "purchase_id")
	private PurchaseHistory purchaseHistory;

	/**
	 * @param id
	 * @param dayOfDelivery
	 * @param addressTbl
	 */
	public OrderDetail(OrderDetailPK id, Timestamp dayOfDelivery, AddressTbl addressTbl) {
		this.id = id;
		this.dayOfDelivery = dayOfDelivery;
		this.addressTbl = addressTbl;
	}

}