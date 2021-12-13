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
 * The persistent class for the address_tbl database table.
 * 
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "address_tbl")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "address_id")
	private String addressId;

	private String city;

	private String relationship; // self, Friend, Father, Mother, Sister, Brother

	private String state;

	private int zip;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customerLogin;

	@OneToMany(mappedBy = "addressTbl")
	private List<OrderDetail> orderDetails;

	/**
	 * @param addressId
	 * @param city
	 * @param name
	 * @param relationship
	 * @param state
	 * @param zip
	 */
	public Address(String addressId, String city, String relationship, String state, int zip) {
		this.addressId = addressId;
		this.city = city;
		this.relationship = relationship;
		this.state = state;
		this.zip = zip;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressId, city, relationship, state, zip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		return Objects.equals(addressId, other.addressId) && Objects.equals(city, other.city)
				&& Objects.equals(relationship, other.relationship) && Objects.equals(state, other.state)
				&& zip == other.zip;
	}

}