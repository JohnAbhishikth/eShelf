package com.lti.shelf.service;

import java.util.List;

import com.lti.shelf.entity.ShoppingCartItem;


public interface ShoppingCartItemService {
	
	public void addShoppingCI(ShoppingCartItem sci);
	public List<ShoppingCartItem> getShoppingCI();
	
	public boolean updateShoppingCI(ShoppingCartItem sci);
	public boolean deleteShoppingCI(ShoppingCartItem sci);

}
