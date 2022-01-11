package com.lti.shelf.service;

import java.util.List;

import com.lti.shelf.dto.ShoppingCartDTO;
import com.lti.shelf.exception.EShelfException;

public interface ShoppingCartItemService {
	void addItemToCart(ShoppingCartDTO shoppingCartDto) throws EShelfException;
	List<ShoppingCartDTO> getCartByUserId(String userId) throws EShelfException;
	boolean updateShoppingCart(ShoppingCartDTO shoppingCartDto) throws EShelfException;
	boolean deleteOneShoppingCartItem(String userId, String inventoryId) throws EShelfException;
	boolean deleteAllShoppingCartItems(String userId) throws EShelfException;
}
