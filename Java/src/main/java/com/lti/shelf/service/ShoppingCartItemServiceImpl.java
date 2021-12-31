package com.lti.shelf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.shelf.dto.ShoppingCartDTO;
import com.lti.shelf.entity.ShoppingCartItem;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.repository.ShoppingCartItemRepository;



@Service
@Transactional
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

	
	@Autowired
	ShoppingCartItemRepository jpaRepo;
	

	@Override
	public void addItemToCart(ShoppingCartDTO shoppingCartDto) throws EShelfException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ShoppingCartDTO> getCartByUserId(String userId) throws EShelfException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateShoppingCart(ShoppingCartDTO shoppingCartDto) throws EShelfException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOneShoppingCartItem(String userId, String inventoryId) throws EShelfException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllShoppingCartItems(String userId) throws EShelfException {
		// TODO Auto-generated method stub
		return false;
	}

	
	/*
	@Override
	public void addShoppingCI(ShoppingCartItem sci) {
		if(sci!=null) 
		{
			try {	
				jpaRepo.save(sci);	
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		
	}

	@Override
	public List<ShoppingCartItem> getShoppingCI() {
		return jpaRepo.findAll();
	}

	@Override
	public boolean updateShoppingCI(ShoppingCartItem sci) {
		if(sci!=null) 
		{
			try {	
				jpaRepo.save(sci);	
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		return true;
	}

	@Override
	public boolean deleteShoppingCI(ShoppingCartItem sci) {
		jpaRepo.delete(sci);
		return true;
	}
*/
}
