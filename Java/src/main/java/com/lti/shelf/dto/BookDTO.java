package com.lti.shelf.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookDTO {
	private String inventoryId;
	private String authorName;
	private int bookCount;
	private String bookName;
	private double price;
}
