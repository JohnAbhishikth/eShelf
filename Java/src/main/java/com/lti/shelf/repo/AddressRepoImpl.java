package com.lti.shelf.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.shelf.entity.Address;
import com.lti.shelf.repository.AddressRepository;

@Repository
public class AddressRepoImpl implements AddressRepo{
	

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

}
