package com.lti.shelf.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDTO {
	private String userId;
	private String email;
	private String name;
	private String phoneNumber;
}
