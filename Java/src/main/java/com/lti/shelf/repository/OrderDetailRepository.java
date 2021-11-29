package com.lti.shelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.shelf.entity.OrderDetail;
import com.lti.shelf.entity.OrderDetailPK;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {

}
