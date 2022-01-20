package com.lti.shelf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lti.shelf.entity.ShoppingCartItem;
import com.lti.shelf.entity.ShoppingCartItemPK;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, ShoppingCartItemPK> {

	@Query(value = "select * from shopping_cart_items where user_id=?1", nativeQuery = true)
	List<ShoppingCartItem> getAllShoppingCartItemsByUserId(String userId);

}
