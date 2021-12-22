package com.lti.shelf.service;

import com.lti.shelf.dto.CustomerDTO;
import com.lti.shelf.dto.LoginDTO;
import com.lti.shelf.exception.EShelfException;

public interface CustomerService {
	void registerCustomer(CustomerDTO customerDto) throws EShelfException;
	void updateCustomer(CustomerDTO customerDto) throws EShelfException;
	CustomerDTO getCustomerById(String userId) throws EShelfException;
	CustomerDTO loginCustomer(LoginDTO loginDTO) throws EShelfException;
}
