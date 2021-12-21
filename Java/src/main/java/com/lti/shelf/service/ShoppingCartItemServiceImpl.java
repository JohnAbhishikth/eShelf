package com.lti.shelf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.shelf.entity.ShoppingCartItem;
import com.lti.shelf.repository.ShoppingCartItemRepository;



@Service
@Transactional
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

	
	@Autowired
	ShoppingCartItemRepository jpaRepo;
	
	
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

}
