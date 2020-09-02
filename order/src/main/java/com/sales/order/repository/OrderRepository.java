package com.sales.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sales.order.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

}

	