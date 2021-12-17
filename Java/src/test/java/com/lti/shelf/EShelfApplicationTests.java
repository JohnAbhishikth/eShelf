package com.lti.shelf;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lti.shelf.entity.Address;
import com.lti.shelf.entity.Book;
import com.lti.shelf.entity.BookReview;
import com.lti.shelf.entity.BookReviewPK;
import com.lti.shelf.entity.Customer;
import com.lti.shelf.entity.OrderDetail;
import com.lti.shelf.entity.OrderDetailPK;
import com.lti.shelf.entity.PurchaseHistory;
import com.lti.shelf.entity.ShoppingCartItem;
import com.lti.shelf.entity.ShoppingCartItemPK;
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

	Address address;
	Book book;
	BookReview bookReview;
	BookReviewPK bookReviewPK;
	Customer customer;
	OrderDetail orderDetail;
	OrderDetailPK orderDetailPK;
	PurchaseHistory purchaseHistory;
	ShoppingCartItem shoppingCartItem;
	ShoppingCartItemPK shoppingCartItemPK;

	@BeforeEach
	public void beforeTest() {
		book = new Book("b1", "Test Author", 15, "Test BookName", 112.52);
		bookRepository.save(book);

		address = new Address("a1", "Vizag", "Self", "AP", 530217);
		Address address2 = addressRepository.save(address);

		customer = new Customer("c1", "email@gmail.com", "Abhi", "p@ssw0rd", "789456");
		ArrayList<Address> list = new ArrayList<>();
		list.add(address);
		customer.setAddressTbls(list);
		Customer save2 = customerRepository.save(customer);

		address2.setCustomerLogin(save2);
		addressRepository.save(address2);

		bookReviewPK = new BookReviewPK(book.getInventoryId(), customer.getUserId());
		bookReview = new BookReview(bookReviewPK, 3, "Good Book");
		bookReviewRepository.save(bookReview);

		purchaseHistory = new PurchaseHistory("ph1", 100, 10, book);
		purchaseHistoryRepository.save(purchaseHistory);

		orderDetailPK = new OrderDetailPK("o1", purchaseHistory.getPurchaseId());
		orderDetail = new OrderDetail(orderDetailPK, null, address);
		orderDetail.setCustomerLogin(customer);
		orderDetailRepository.save(orderDetail);

		shoppingCartItemPK = new ShoppingCartItemPK(book.getInventoryId(), customer.getUserId());
		shoppingCartItem = new ShoppingCartItem(shoppingCartItemPK, 500, 3);
		shoppingCartItemRepository.save(shoppingCartItem);
	}

	@Override
	public void createCustomer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectCustomer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCustomer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCustomer() {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void createBook() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectBook() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBook() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBook() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createBookReview() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectBookReview() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBookReview() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBookReview() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createOrderDetails() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectOrderDetails() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOrderDetails() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOrderDetails() {
		// TODO Auto-generated method stub

	}


	@Override
	@Test
	public void createAddress() {
		Address ad = new Address();
		Customer cust = new Customer();
		
		cust.setUserId("c2");
		customerRepository.save(cust);
		
		
		ad.setAddressId("a2");
		ad.setCity("Vijayawada");
		ad.setRelationship("Friend");
		ad.setState("Andhra Pradesh");
		ad.setZip(521311);
		ad.setCustomerLogin(cust);
		Address address = addressRepository.save(ad);
		assertEquals(address, ad);
		
	}

	@Override
	@Test
	public void selectAddress() {
		Optional<Address> findById = addressRepository.findById("a1");
		if (findById.isPresent())
			assertEquals(findById.get(), address);
		else {
			System.out.println("from else");
			assertEquals(findById.get(), address);
		}
	}
	
	

	@Override
	@Test
	public void updateAddress() {
		Address add = addressRepository.findById("a1").get();
		add.setCity("Guntur");
		Address save = addressRepository.save(add);
		assertNotEquals(address, save);
	}

	
	
	@Override
	@Test
	public void deleteAddress() {
		addressRepository.deleteById("a1");
		System.out.println();
		assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> {
			addressRepository.findById("a1").get();
		});
	}


	@Override
	@Test
	public void createPurchaseHistory() {
		PurchaseHistory purchaseHistory = new PurchaseHistory();
		Book book = new Book();
		book.setInventoryId("id1");
		bookRepository.save(book);
		purchaseHistory.setBook(book);
		purchaseHistory.setPrice(100);
		purchaseHistory.setPurchaseId("p123");
		purchaseHistory.setQuantity(10);
		purchaseHistory.setOrderDetails(null);
		PurchaseHistory history = purchaseHistoryRepository.save(purchaseHistory);
		assertEquals(history, purchaseHistory);
	}

	@Override
	@Test
	public void selectPurchaseHistory() {
		Optional<PurchaseHistory> findById = purchaseHistoryRepository.findById("p123");
		if (findById.isPresent())
			assertEquals(findById.get(), purchaseHistory);
	}

	@Override
	@Test
	public void updatePurchaseHistory() {
		PurchaseHistory purchase = purchaseHistoryRepository.findById("p123").get();
		purchase.setPrice(200);
		PurchaseHistory save = purchaseHistoryRepository.save(purchase);
		assertNotEquals(purchaseHistory, save);

	}

	@Override
	@Test
	public void deletePurchaseHistory() {
		purchaseHistoryRepository.deleteById("ph1");
		assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> {
			purchaseHistoryRepository.findById("ph1").get();
		});
	}

	@Override
	@Test
	public void createShoppingCartItem() {
	
		ShoppingCartItem sc = new ShoppingCartItem();
		
		sc.setId(shoppingCartItemPK);
		sc.setPrice(200);
		sc.setQuantity(10);
		
		ShoppingCartItem cartitem= shoppingCartItemRepository.save(sc);
		
		assertEquals(cartitem, sc);	

	}

	@Override
	@Test
	public void selectShoppingCartItem() {
		Optional<ShoppingCartItem> findById = shoppingCartItemRepository.findById(shoppingCartItemPK);
		if (findById.isPresent())
			assertEquals(findById.get(),  shoppingCartItem);
		else {
			System.out.println("from else");
			assertEquals(findById.get(),  shoppingCartItem);
		}
	}
	
	
	@Override
	@Test
	public void updateShoppingCartItem() {
		ShoppingCartItem scitem = shoppingCartItemRepository.findById(shoppingCartItemPK).get();
		scitem.setPrice(200);
		ShoppingCartItem save = shoppingCartItemRepository.save(scitem);
		assertNotEquals( shoppingCartItem, save);
		}

	@Override
	@Test
	public void deleteShoppingCartItem() {
		shoppingCartItemRepository.deleteById(shoppingCartItemPK); 
		assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> {
			shoppingCartItemRepository.findById(shoppingCartItemPK).get();
		});
	}
}
