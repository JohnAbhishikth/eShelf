package com.lti.shelf.service;

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
			if (!customer.isPresent()) {
				throw new EShelfException("user not available");
			}
			// Address address = new Address(addressDto.getAddressId(),
			// addressDto.getRelationship(), addressDto.getCity(), addressDto.getState(),
			// addressDto.getZip());

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
				for (Address address : findAllById) {
					AddressDTO addressDto = new AddressDTO();
					addressDto.setAddressId(address.getAddressId());
					addressDto.setRelationship(address.getRelationship());
					addressDto.setCity(address.getCity());
					addressDto.setState(address.getState());
					addressDto.setZip(address.getZip());
					addressDto.setUserId(userId);
					addressDTOList.add(addressDto);
				}
			} catch (Exception e) {
				throw new EShelfException("No Details");
			}
			return addressDTOList;
		} else {
			throw new EShelfException("user id cant be null");
		}
	}

	@Override
	public boolean updateAddress(AddressDTO addressDto) throws EShelfException {
		if (addressDto != null) {
			Optional<Customer> custOptional = customerRepository.findById(addressDto.getUserId());
			if (!custOptional.isPresent()) {
				throw new EShelfException("user not present");
			}
			
			Optional<Address> addressOptional = addressRepository.findById(addressDto.getAddressId());
			if (!addressOptional.isPresent()) {
				throw new EShelfException("address is not present");
			} else {
				Address address = addressOptional.get();
				if (address.getCustomerLogin().getUserId().equals(addressDto.getUserId())) {
					try {
						address.setAddressId(addressDto.getAddressId());
						address.setRelationship(addressDto.getRelationship());
						address.setCity(addressDto.getCity());
						address.setState(addressDto.getState());
						address.setZip(addressDto.getZip());
						addressRepository.save(address);
					} catch (Exception e) {
						throw new EShelfException("Invalid Address");
					}	
				}else {
					throw new EShelfException("Cutomer and Address don't match");
				}
			}
		} else {
			throw new EShelfException("user id cannot be null");
		}
		return true;
	}

	@Override
	public boolean deleteAddress(String addressId, String userId) throws EShelfException {
		if (addressId != null && userId != null) {
			Optional<Customer> customerOptional = customerRepository.findById(userId);
			if (!customerOptional.isPresent()) {
				throw new EShelfException("user not present");
			}
			Customer cust = customerOptional.get();
			
			Optional<Address> addressOptional = addressRepository.findById(addressId);
			if(!addressOptional.isPresent()) {
				throw new EShelfException("Address is Not registered");
			}
			Address address = addressOptional.get();
			if (addressOptional.isPresent() && address.getCustomerLogin().getUserId().equals(userId) ) {	
				try { 
					address.setCustomerLogin(cust);
					address.setAddressId(addressId);

					addressRepository.delete(address);
				} catch (Exception e) {
					throw new EShelfException("Invalid details");
				}
			} else {
				throw new EShelfException("Address doesn't belongs to Customer");
			}
		} else {
			throw new EShelfException("addressID or userID not be Null");
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
			} catch (Exception e) {
				throw new EShelfException("customer is not available");
			}
			return customerDto;
		} else {
			throw new EShelfException("user id cant be null");
		}
	}
}
