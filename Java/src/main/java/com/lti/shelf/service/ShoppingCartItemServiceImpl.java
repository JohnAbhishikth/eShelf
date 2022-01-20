package com.lti.shelf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.Stoppable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.shelf.dto.ShoppingCartDTO;
import com.lti.shelf.entity.Book;
import com.lti.shelf.entity.ShoppingCartItem;
import com.lti.shelf.entity.ShoppingCartItemPK;
import com.lti.shelf.exception.EShelfException;
import com.lti.shelf.repository.BookRepository;
import com.lti.shelf.repository.ShoppingCartItemRepository;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

	@Autowired
	ShoppingCartItemRepository shoppingCartRepository;
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public void addItemToCart(ShoppingCartDTO shoppingCartDto) throws EShelfException {

			if (shoppingCartDto == null) {
				throw new EShelfException("ShoppingCart Item Cant be null");
			}

			Optional<Book> findById = bookRepository.findById(shoppingCartDto.getInventoryId());
			if(!findById.isPresent()) {
				throw new EShelfException("Book is not available");
			}			
			Book book = findById.get();
			
			ShoppingCartItemPK cartItemPK = new ShoppingCartItemPK(shoppingCartDto.getInventoryId(),shoppingCartDto.getUserId());
			Optional<ShoppingCartItem> cartOptional = shoppingCartRepository.findById(cartItemPK);
			try {
				if(cartOptional.isPresent()) {	
						ShoppingCartItem shoppingCartItem = cartOptional.get();
						shoppingCartItem.setId(cartItemPK);
						int quantity =shoppingCartDto.getQuantity() + shoppingCartItem.getQuantity();
						shoppingCartItem.setQuantity(quantity);
						if(book.getBookCount() < quantity) { 
							throw new EShelfException("out of Stock!!!");
						}
						Double price = book.getPrice() * quantity;
						shoppingCartItem.setPrice(price);
						shoppingCartRepository.save(shoppingCartItem);
					}else {
						ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
						shoppingCartItem.setId(cartItemPK);
						shoppingCartItem.setPrice(book.getPrice());
						shoppingCartItem.setQuantity(1);
						shoppingCartRepository.save(shoppingCartItem);
					}
			}catch(Exception e) {
				throw new EShelfException("unable to add Item in to cart");
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
			throw new EShelfException("Cart is Empty");

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

		if (shoppingCartDto == null) {
			throw new EShelfException("ShoppingCart Item Cant be null");
		}

		Optional<Book> findById = bookRepository.findById(shoppingCartDto.getInventoryId());
		if(!findById.isPresent()) {
			throw new EShelfException("Book is not available");
		}			
		Book book = findById.get();
		
		ShoppingCartItemPK cartItemPK = new ShoppingCartItemPK(shoppingCartDto.getInventoryId(),shoppingCartDto.getUserId());
		Optional<ShoppingCartItem> cartOptional = shoppingCartRepository.findById(cartItemPK);
		try {
			if(cartOptional.isPresent()) {	
				ShoppingCartItem shoppingCartItem = cartOptional.get();
				shoppingCartItem.setId(cartItemPK);
				int quantity = shoppingCartItem.getQuantity() - shoppingCartDto.getQuantity();
				shoppingCartItem.setQuantity(quantity);
				if(quantity <= 0) {
					throw new EShelfException("cart is empty!!!");
				}
				Double price = book.getPrice() * quantity;
				shoppingCartItem.setPrice(price);
				shoppingCartRepository.save(shoppingCartItem);
				}
			else {
				throw new EShelfException("customer is not registered!!!");
			}
		}catch(Exception e) {
			throw new EShelfException(e.getMessage());
		}
		return true;
		

	}



	@Override
	public boolean deleteOneShoppingCartItem(String userId, String inventoryId) throws EShelfException {
		try {
			ShoppingCartItemPK shoppingCartItemPK = new ShoppingCartItemPK(inventoryId, userId);
			shoppingCartRepository.deleteById(shoppingCartItemPK);
			return true;
		} catch (Exception e) {
			throw new EShelfException("cart item not available to delete");
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
			throw new EShelfException("No items are available to delete");
		}
	}
}
