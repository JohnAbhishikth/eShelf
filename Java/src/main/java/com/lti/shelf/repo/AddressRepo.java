package com.lti.shelf.repo;

import java.util.List;

import com.lti.shelf.entity.Address;


public interface AddressRepo {
	
	public void addAddress(Address address);
	public List<Address> getAddress();
	
	public boolean updateAddress(Address address);
	public boolean deleteAddress(Address address);

}
