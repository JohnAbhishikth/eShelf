package com.lti.shelf.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookReviewDTO {
	int userId;
	String inventoryId;
	int rating;
	String reviews;

}
