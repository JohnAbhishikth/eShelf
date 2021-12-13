package com.lti.shelf.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The primary key class for the shopping_cart_items database table.
 * 
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ShoppingCartItemPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "inventory_id", insertable = false, updatable = false)
	private String inventoryId;

	@Column(name = "user_id", insertable = false, updatable = false)
	private String userId;

}