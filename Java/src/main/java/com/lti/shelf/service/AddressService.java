package com.lti.shelf.service;

import java.util.List;

import com.lti.shelf.dto.AddressDTO;
import com.lti.shelf.dto.CustomerDTO;
import com.lti.shelf.exception.EShelfException;

public interface AddressService {

	void addAddress(AddressDTO addressDto) throws EShelfException;
	List<AddressDTO> getAddressByUserId(String userId) throws EShelfException;
	boolean updateAddress(AddressDTO addressDto) throws EShelfException;
	boolean deleteAddress(String addressId, String userId) throws EShelfException;
	CustomerDTO searchCustomerByAddressId(String addressId) throws EShelfException;

}
