package com.sales.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sales.order.model.Order;
import com.sales.order.repository.OrderRepository;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    // Find
    //@GetMapping("/orders")
    @GetMapping
    List<Order> findAll() {
        return repository.findAll();
    }

    // Save
    @PostMapping
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Order createOrder(@RequestBody Order newOrder) {
        return repository.save(newOrder);
    }

/*
    // Find
    @GetMapping("/orders/{id}")
    Order findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException());
    }
*/
    // Save or update
    @PutMapping("/{id}")
    Order saveOrUpdate(@RequestBody Order newOrder, @PathVariable Long id) {
        return repository.findById(id)
                .map(x -> {
                		x.setCustomerName(newOrder.getCustomerName());
                		x.setOrderDate(newOrder.getOrderDate());
                		x.setShippingAddress(newOrder.getShippingAddress());
                		x.setTotal(newOrder.getTotal());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                	newOrder.setOrderItem(id);
                    return repository.save(newOrder);
                });
    }
    

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }


}