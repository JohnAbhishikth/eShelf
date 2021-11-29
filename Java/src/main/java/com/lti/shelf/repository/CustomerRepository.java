package com.lti.shelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.shelf.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
