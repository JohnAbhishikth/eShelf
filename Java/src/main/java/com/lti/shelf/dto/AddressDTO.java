package com.lti.shelf.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDTO {
	private String addressId;
	private String city;
	private String relationship; // self, Friend, Father, Mother, Sister, Brother
	private String state;
	private int zip;
}
