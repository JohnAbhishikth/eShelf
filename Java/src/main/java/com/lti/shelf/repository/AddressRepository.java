package com.lti.shelf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lti.shelf.entity.Address;

public interface AddressRepository extends JpaRepository<Address, String> {

	@Query(value = "SELECT * FROM address_tbl WHERE user_id=?1", nativeQuery = true)
	List<Address> findAllByUserId(String userId);

}
