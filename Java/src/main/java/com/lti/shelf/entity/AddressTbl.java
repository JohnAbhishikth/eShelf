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
 * The persistent class for the address_tbl database table.
 * 
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "address_tbl")
public class AddressTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "address_id")
	private String addressId;

	private String city;

	private String name;

	private String relationship;

	private String state;

	private int zip;

	// bi-directional many-to-one association to CustomerLogin
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customerLogin;

	// bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy = "addressTbl")
	private List<OrderDetail> orderDetails;

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setAddressTbl(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setAddressTbl(null);

		return orderDetail;
	}

	/**
	 * @param addressId
	 * @param city
	 * @param name
	 * @param relationship
	 * @param state
	 * @param zip
	 */
	public AddressTbl(String addressId, String city, String name, String relationship, String state, int zip) {
		this.addressId = addressId;
		this.city = city;
		this.name = name;
		this.relationship = relationship;
		this.state = state;
		this.zip = zip;
	}

}