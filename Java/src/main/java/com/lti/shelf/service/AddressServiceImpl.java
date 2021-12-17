package com.lti.shelf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.shelf.entity.Address;
import com.lti.shelf.repo.AddressRepo;
import com.lti.shelf.repository.AddressRepository;


@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository jpaRepo;

	
	@Override
	public void addAddress(Address address) {
		jpaRepo.save(address);
	}

	@Override
	public List<Address> getAddress() {
		return jpaRepo.findAll();
	}

	@Override
	public boolean updateAddress(Address address) {
		jpaRepo.save(address);
		return true;
	}

	@Override
	public boolean deleteAddress(Address address) {
		jpaRepo.delete(address);
		return true;
	}

	@Override
	public Address searchCustomerByCustId(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

}
