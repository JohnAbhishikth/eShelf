package com.lti.shelf.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

@Controller
@CrossOrigin
public class DataController {

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

	@GetMapping("/data")
	@ResponseBody
	public String loadTestData() {
		book = new Book("b1", "Test Author", "Technology", 15, "Test BookName", 112.52);
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
		return "Data Submitted Succesfully";
	}
}
