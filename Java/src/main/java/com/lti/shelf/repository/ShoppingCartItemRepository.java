package com.lti.shelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.shelf.entity.ShoppingCartItem;
import com.lti.shelf.entity.ShoppingCartItemPK;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, ShoppingCartItemPK> {

}
