package com.lti.shelf.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShoppingCartDTO {
	private String inventoryId;
	private String userId;
	private double price;
	private int quantity;
}
