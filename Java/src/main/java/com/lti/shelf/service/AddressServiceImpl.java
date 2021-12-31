package com.lti.shelf.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.shelf.dto.AddressDTO;
import com.lti.shelf.dto.CustomerDTO;
import com.lti.shelf.entity.Address;
import com.lti.shelf.entity.Customer;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.repository.AddressRepository;
import com.lti.shelf.repository.CustomerRepository;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void addAddress(AddressDTO addressDto) throws EShelfException {

		if (addressDto != null) {
			Optional<Address> findById = addressRepository.findById(addressDto.getAddressId());
			if (findById.isPresent())
				throw new EShelfException("Address is already available");

			Optional<Customer> customer = customerRepository.findById(addressDto.getUserId());
			if(!customer.isPresent()) {
				throw new EShelfException("user not present");
			}
			// Address address = new Address(addressDto.getAddressId(), addressDto.getRelationship(), addressDto.getCity(), addressDto.getState(), addressDto.getZip());

			Address address = new Address();
			
			try {
				address.setAddressId(addressDto.getAddressId());
				address.setRelationship(addressDto.getRelationship());
				address.setCity(addressDto.getCity());
				address.setState(addressDto.getState());
				address.setZip(addressDto.getZip());
				address.setCustomerLogin(customer.get());
				addressRepository.save(address);
			} catch (Exception e) {
				throw new EShelfException("Invalid Address");
			}
		} else {
			System.out.println("Enter all details");
		}
	}

	@Override
	public List<AddressDTO> getAddressByUserId(String userId) throws EShelfException {
		if (userId != null) {
			List<Address> findAllById = addressRepository.findAllByUserId(userId);

			List<AddressDTO> addressDTOList = new ArrayList<>();
			
			try {
				for(Address address:findAllById) {
					AddressDTO addressDto = new AddressDTO();
					addressDto.setAddressId(address.getAddressId());
					addressDto.setRelationship(address.getRelationship());
					addressDto.setCity(address.getCity());
					addressDto.setState(address.getState());
					addressDto.setZip(address.getZip());
					addressDto.setUserId(userId);
					addressDTOList.add(addressDto);
				}
			}catch(Exception e) {
				throw new EShelfException("No Details");
			}
			return addressDTOList;
		}
		else {
			throw new EShelfException("user id cant be null");	
		}
	}
		

	@Override
	public boolean updateAddress(AddressDTO addressDto, String userId) throws EShelfException {
		if (addressDto != null) {
			Optional<Customer> customer = customerRepository.findById(userId);
			if(!customer.isPresent()) {
				throw new EShelfException("user not present");
			}
			
			Optional<Address> findByUserId = addressRepository.findById(addressDto.getAddressId());
			if (!findByUserId.isPresent()) {
				throw new EShelfException("address is not present");
			}else {
				
				Address address = new Address();
				try {
					address.setAddressId(addressDto.getAddressId());
					address.setRelationship(addressDto.getRelationship());
					address.setCity(addressDto.getCity());
					address.setState(addressDto.getState());
					address.setZip(addressDto.getZip());
					address.setCustomerLogin(customer.get());
					
					addressRepository.save(address);
				} catch (Exception e) {
					throw new EShelfException("Invalid Address");
				}
			}	
		}else {
			throw new EShelfException("user id cannot be null");
		}
		return true;
	}

	@Override
	public boolean deleteAddress(String addressId, String userId) throws EShelfException {
		if(addressId != null) {
			Optional<Customer> customer = customerRepository.findById(userId);
			if(!customer.isPresent()) {
				throw new EShelfException("user not present");
			}
			
			Optional<Address> findById = addressRepository.findById(addressId); 
			if (findById.isPresent()) {
				Address address = new Address();
				try {
					address.setAddressId(addressId);
					
					addressRepository.delete(address);
				} catch (Exception e) {
					throw new EShelfException("Invalid Address");
					}
				}else {
					throw new EShelfException("address is not present");
				}
			}else {
				throw new EShelfException("addressID not be Null");
			}
		return true;
	}

	@Override
	public CustomerDTO searchCustomerByAddressId(String addressId) throws EShelfException {
		
		if (addressId != null) {
			CustomerDTO customerDto = new CustomerDTO();
			
			Optional<Address> findAddress = addressRepository.findById(addressId);
			
			Address address = findAddress.get();
			String userId = address.getCustomerLogin().getUserId();
			
			Optional<Customer> findCustomer = customerRepository.findById(userId);
			Customer customer = findCustomer.get();
			
			try {
				customerDto.setUserId(customer.getUserId());
				customerDto.setName(customer.getName());
				customerDto.setEmail(customer.getEmail());
				customerDto.setPhoneNumber(customer.getPhoneNumber());
			}catch(Exception e) {
				throw new EShelfException("No Customer is present");
			}	
			return customerDto;
		}else {
			throw new EShelfException("user id cant be null");	
		}
	}
}
