package com.lti.shelf.service;

import java.util.List;

import com.lti.shelf.dto.AddressDTO;
import com.lti.shelf.dto.CustomerDTO;
import com.lti.shelf.exception.EShelfException;

public interface AddressService {
<<<<<<< HEAD
	
	public void addAddress(Address address);
	public List<Address> getAddress();
	
	public boolean updateAddress(Address address);
	public boolean deleteAddress(String addId);

	public Address searchCustomerByCustId(String addId);
=======
	void addAddress(AddressDTO addresDto) throws EShelfException;
	List<AddressDTO> getAddressByUserId(String userId) throws EShelfException;
	boolean updateAddress(AddressDTO addresDto, String userId) throws EShelfException;
	boolean deleteAddress(String addressId, String userId) throws EShelfException;
	CustomerDTO searchCustomerByAddressId(String addressId) throws EShelfException;
>>>>>>> 5cbb907ec8f4ecafc048004cd76201fdb064f696
}
