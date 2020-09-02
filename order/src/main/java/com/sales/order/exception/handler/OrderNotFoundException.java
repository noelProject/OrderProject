package com.sales.order.exception.handler;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("Order not found : " + id);
    }

}