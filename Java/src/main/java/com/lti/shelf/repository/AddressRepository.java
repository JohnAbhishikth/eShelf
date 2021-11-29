package com.lti.shelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.shelf.entity.Address;

public interface AddressRepository extends JpaRepository<Address, String> {

}
