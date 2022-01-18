package com.lti.shelf.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.shelf.dto.CustomerDTO;
import com.lti.shelf.dto.LoginDTO;
import com.lti.shelf.entity.Customer;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepo;

	@Override
	public void registerCustomer(CustomerDTO customerDto) throws EShelfException {
		if (customerDto != null && customerDto.getUserId() != null) {
			Optional<Customer> findByID = customerRepo.findById(customerDto.getUserId());
			if (findByID.isPresent())
				throw new EShelfException("Customer already Exist");
			Customer customer = new Customer();
			try {
				customer.setUserId(customerDto.getUserId());
				customer.setEmail(customerDto.getEmail());
				customer.setName(customerDto.getName());
				customer.setPhoneNumber(customerDto.getPhoneNumber());
				customer.setPassword(customerDto.getPassword());
				customerRepo.save(customer);
			} catch (Exception e) {
				throw new EShelfException("Invalid Customer! Try Again");
			}
		} 
	}

	@Override
	public void updateCustomer(CustomerDTO customerDto) throws EShelfException {
		if (customerDto != null) {
			Optional<Customer> cust = customerRepo.findById(customerDto.getUserId());
			if (!cust.isPresent())
				throw new EShelfException("Enter valid Customer");
			try {
				Customer customer = cust.get();
				//customer.setUserId(customerDto.getUserId());
				customer.setName(customerDto.getName());
				customer.setEmail(customerDto.getEmail());
				customer.setPhoneNumber(customerDto.getPhoneNumber());
				customer.setPassword(customerDto.getPassword());
				customerRepo.save(customer);
			} catch (Exception e) {
				throw new EShelfException("Invalid Customer");
			}
		} else {
			throw new EShelfException("Invalid Customer");
		}
	}

	@Override
	public CustomerDTO getCustomerById(String userId) throws EShelfException {
		if (userId == null) {
			throw new EShelfException("UserId can't be null");	
		}
		Optional<Customer> findByID = customerRepo.findById(userId);
		if (!findByID.isPresent())
			throw new EShelfException("Can not find Customer");
		Customer customer = findByID.get();
		CustomerDTO dto = new CustomerDTO();
		dto.setName(customer.getName());
		dto.setEmail(customer.getEmail());
		dto.setPhoneNumber(customer.getPhoneNumber());
		dto.setUserId(customer.getUserId());
		//dto.setPassword(customer.getPassword());
		return dto;
	}

	@Override
	public CustomerDTO loginCustomer(LoginDTO loginDTO) throws EShelfException {
		if (loginDTO==null||loginDTO.getLoginId()==null) {
			throw new EShelfException("Login Credentials must not be null");
		}
		Optional<Customer> customer = customerRepo.findById(loginDTO.getLoginId());
		if (!customer.isPresent())
			throw new EShelfException("Invalid UserId");
		Customer cust = customer.get();
		String password = cust.getPassword();
		if (password.equals(loginDTO.getPassword())) {
			CustomerDTO dto = new CustomerDTO();
			dto.setName(cust.getName());
			dto.setEmail(cust.getEmail());
			dto.setPhoneNumber(cust.getPhoneNumber());
			dto.setUserId(cust.getUserId());
			return dto;
		} else {
			throw new EShelfException("Invalid Password");
		}
	}
}
