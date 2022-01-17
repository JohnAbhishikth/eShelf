
package com.lti.shelf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;
import com.lti.shelf.dto.CustomerDTO;
import com.lti.shelf.dto.LoginDTO;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@PostMapping("/register")
	@ResponseBody
	public String registerCustomer(@RequestBody CustomerDTO customerDTO) {
		try {
			customerService.registerCustomer(customerDTO);
			return "Registration Completed";
		} catch (EShelfException e) {
			return e.getMessage();
		}
	}
	
	@GetMapping("/get/{id}")
	@ResponseBody
	public CustomerDTO getCustomer(@PathVariable String id) throws EShelfException {
		return customerService.getCustomerById(id);
	}
	@PostMapping("/update")
	@ResponseBody
	public String updateCustomer(@RequestBody CustomerDTO customerDTO) {
		try {
			customerService.updateCustomer(customerDTO);
			return "Details Updated Successfully";
		} catch (EShelfException e) {
			return e.getMessage();
		}
	}
	@PostMapping("/login")
	public CustomerDTO loginCustomer(@RequestBody LoginDTO loginDto) throws EShelfException {
		try {
			CustomerDTO dto = customerService.loginCustomer(loginDto);
			return dto;
		} catch (EShelfException e) {
			throw e;
		}
		 
	}
	
}