package com.lti.shelf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.shelf.dto.ShoppingCartDTO;
import com.lti.shelf.entity.ShoppingCartItem;
import com.lti.shelf.entity.ShoppingCartItemPK;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.repository.ShoppingCartItemRepository;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

	@Autowired
	ShoppingCartItemRepository shoppingCartRepository;

	@Override
	public void addItemToCart(ShoppingCartDTO shoppingCartDto) throws EShelfException {

		try {
			if (shoppingCartDto == null) {
				throw new EShelfException("ShoppingCart Item Cant be null");
			}

			ShoppingCartItemPK cartItemPK = new ShoppingCartItemPK(shoppingCartDto.getInventoryId(),
					shoppingCartDto.getUserId());
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem(cartItemPK, shoppingCartDto.getPrice(),
					shoppingCartDto.getQuantity());

			shoppingCartRepository.save(shoppingCartItem);
		} catch (Exception e) {
			throw new EShelfException("Unable to add to cart");
		}
	}

	@Override
	public List<ShoppingCartDTO> getCartByUserId(String userId) throws EShelfException {
		if (userId == null || userId.length() == 0) {
			throw new EShelfException("Enter Valid User Id");
		}

		List<ShoppingCartItem> allShoppingCartItemsByUserId = shoppingCartRepository
				.getAllShoppingCartItemsByUserId(userId);
		if (allShoppingCartItemsByUserId.isEmpty())
			throw new EShelfException("No details");

		List<ShoppingCartDTO> shoppingCartDTOList = new ArrayList<>();
		try {
			for (ShoppingCartItem shoppingCartItem : allShoppingCartItemsByUserId) {
				ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
				shoppingCartDTO.setInventoryId(shoppingCartItem.getBook().getInventoryId());
				shoppingCartDTO.setPrice(shoppingCartItem.getPrice());
				shoppingCartDTO.setQuantity(shoppingCartItem.getQuantity());
				shoppingCartDTO.setUserId(userId);
				shoppingCartDTOList.add(shoppingCartDTO);
			}
			return shoppingCartDTOList;
		} catch (Exception e) {
			throw new EShelfException(e.getMessage());
		}

	}

	@Override
	public boolean updateShoppingCart(ShoppingCartDTO shoppingCartDto) throws EShelfException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOneShoppingCartItem(String userId, String inventoryId) throws EShelfException {
		try {
			ShoppingCartItemPK shoppingCartItemPK = new ShoppingCartItemPK(inventoryId, userId);
			shoppingCartRepository.deleteById(shoppingCartItemPK);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean deleteAllShoppingCartItems(String userId) throws EShelfException {
		try {
			List<ShoppingCartDTO> cartByUserId = this.getCartByUserId(userId);
			for (ShoppingCartDTO dto : cartByUserId) {
				ShoppingCartItemPK shoppingCartItemPK = new ShoppingCartItemPK(dto.getInventoryId(), dto.getUserId());
				shoppingCartRepository.deleteById(shoppingCartItemPK);
			}
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * @Override public void addShoppingCI(ShoppingCartItem sci) { if(sci!=null) {
	 * try { jpaRepo.save(sci); }catch(Exception e) { System.out.print(e); } }
	 * 
	 * }
	 * 
	 * @Override public List<ShoppingCartItem> getShoppingCI() { return
	 * jpaRepo.findAll(); }
	 * 
	 * @Override public boolean updateShoppingCI(ShoppingCartItem sci) {
	 * if(sci!=null) { try { jpaRepo.save(sci); }catch(Exception e) {
	 * System.out.print(e); } } return true; }
	 * 
	 * @Override public boolean deleteShoppingCI(ShoppingCartItem sci) {
	 * jpaRepo.delete(sci); return true; }
	 */
}
