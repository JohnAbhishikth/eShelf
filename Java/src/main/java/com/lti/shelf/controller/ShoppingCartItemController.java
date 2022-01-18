package com.lti.shelf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.lti.shelf.dto.ShoppingCartDTO;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.service.ShoppingCartItemService;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class ShoppingCartItemController {

	/*
	 * show cart items addItem to the cart updateItemQuantity remove an single item
	 * remove all Items
	 * 
	 * localhost:8080/cart/show/c1
	 */

	@Autowired
	ShoppingCartItemService shoppingCartItemService;

	@GetMapping("/show/{userId}")
	public List<ShoppingCartDTO> showShoopingCartItems(@PathVariable String userId) throws EShelfException {
		return shoppingCartItemService.getCartByUserId(userId);

	}

	@PostMapping("/add")
	@ResponseBody
	public String addShoppingCartItem(@RequestBody ShoppingCartDTO shoppingCartDto) throws EShelfException {
		shoppingCartItemService.addItemToCart(shoppingCartDto);
		return "item added successfully";
	}

	@PutMapping("/update")
	@ResponseBody
	public String updateShoppingCartItem(@RequestBody ShoppingCartDTO shoppingCartDto) {
		return null;

	}

	@DeleteMapping("/remove")
	public String removeCartItem(@RequestBody ShoppingCartDTO shoppingCartDto) throws EShelfException {
		boolean bool = shoppingCartItemService.deleteOneShoppingCartItem(shoppingCartDto.getUserId(),
				shoppingCartDto.getInventoryId());
		if (bool)
			return "deleted Successfully";
		else {
			return "unable to delete";
		}
	}

	@DeleteMapping("/removeall")
	@ResponseBody
	public String removeAllCartItems(@RequestBody ShoppingCartDTO shoppingCartDto) throws EShelfException {
		boolean bool = shoppingCartItemService.deleteAllShoppingCartItems(shoppingCartDto.getUserId());
		if (bool)
			return "deleted Successfully";
		else {
			return "unable to delete";
		}
	}
}
