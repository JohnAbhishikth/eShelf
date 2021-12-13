package com.lti.shelf;
/*
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

 CRUD
 
 Create
 Read
 Update
 Delete
 */


public interface Tests {

	void createCustomer();
	void selectCustomer();
	void updateCustomer();
	void deleteCustomer();
	
	/************************************/
	
	void createBook();
	void selectBook();
	void updateBook();
	void deleteBook();
	
	/************************************/
	
	void createBookReview();
	void selectBookReview();
	void updateBookReview();
	void deleteBookReview();
	
	/************************************/
	
	void createOrderDetails();
	void selectOrderDetails();
	void updateOrderDetails();
	void deleteOrderDetails();
	
	/************************************/
	
	void createAddress();
	void selectAddress();
	void updateAddress();
	void deleteAddress();
	
	/************************************/
	
	void createPurchaseHistory();
	void selectPurchaseHistory();
	void updatePurchaseHistory();
	void deletePurchaseHistory();
	
	/************************************/
	
	void createShoppingCartItem();
	void selectShoppingCartItem();
	void updateShoppingCartItem();
	void deleteShoppingCartItem();
	
	/************************************/
}
