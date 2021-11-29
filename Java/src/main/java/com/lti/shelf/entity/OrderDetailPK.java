package com.lti.shelf.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The primary key class for the order_details database table.
 * 
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderDetailPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "order_id")
	private String orderId;

	@Column(name = "purchase_id", insertable = false, updatable = false)
	private String purchaseId;

}