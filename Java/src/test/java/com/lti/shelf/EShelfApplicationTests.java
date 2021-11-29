package com.lti.shelf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lti.shelf.repository.AddressRepository;
import com.lti.shelf.repository.BookRepository;
import com.lti.shelf.repository.BookReviewRepository;
import com.lti.shelf.repository.CustomerRepository;
import com.lti.shelf.repository.OrderDetailRepository;
import com.lti.shelf.repository.PurchaseHistoryRepository;
import com.lti.shelf.repository.ShoppingCartItemRepository;

@SpringBootTest
class EShelfApplicationTests implements Tests {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	BookReviewRepository bookReviewRepository;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	PurchaseHistoryRepository purchaseHistoryRepository;
	@Autowired
	ShoppingCartItemRepository shoppingCartItemRepository;

	@Override
	@Test
	public void createCustomer() {
		// TODO Auto-generated method stub
		
	}

}
