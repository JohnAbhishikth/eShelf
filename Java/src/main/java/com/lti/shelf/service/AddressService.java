package com.lti.shelf.service;

import java.util.List;

import com.lti.shelf.entity.Address;


public interface AddressService {
	
	public void addAddress(Address address);
	public List<Address> getAddress();
	
	public boolean updateAddress(Address address);
	public boolean deleteAddress(String addId);

	public Address searchCustomerByCustId(String addId);
}
