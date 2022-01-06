package com.lti.shelf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.shelf.dto.AddressDTO;
import com.lti.shelf.dto.CustomerDTO;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.service.AddressService;

@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {
	@Autowired
	AddressService addressService;

	@PostMapping("/add")
	@ResponseBody
	public String addAddress(@RequestBody AddressDTO addressDTO) {
		try {
			addressService.addAddress(addressDTO);
		} catch (EShelfException e) {
			return e.getMessage();
		}
		return "address added Successfully";
	}

	@PutMapping("/update")
	@ResponseBody
	public String updateAddress(@RequestBody AddressDTO addressDTO) {
		try {
			addressService.updateAddress(addressDTO);
		} catch (EShelfException e) {
			return e.getMessage();
		}
		return "updated Successfully";
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public String deleteAddress(@RequestBody AddressDTO addressDTO) {
		try {
			addressService.deleteAddress(addressDTO.getAddressId(), addressDTO.getUserId());

		} catch (EShelfException e) {
			return e.getMessage();
		}
		return "Deleted Successfully";
	}

	@GetMapping("/get/{userId}")
	public List<AddressDTO> getAddressById(@PathVariable String userId) throws EShelfException {

		List<AddressDTO> addressList = addressService.getAddressByUserId(userId);

		return addressList;
	}

	@GetMapping("/get/customer/{addressId}")
	public CustomerDTO getCustomerById(@PathVariable String addressId) throws EShelfException {

		CustomerDTO customer = addressService.searchCustomerByAddressId(addressId);

		return customer;
	}

}
