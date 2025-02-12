package com.bakebuddy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakebuddy.exception.OrderException;
import com.bakebuddy.model.OrderItem;
import com.bakebuddy.repository.OrderItemRepository;
import com.bakebuddy.service.OrderItemService;

import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {


    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public OrderItem getOrderItemById(Long id) throws Exception {

        System.out.println("------- "+id);
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if(orderItem.isPresent()){
            return orderItem.get();
        }
        throw new OrderException("Order item not found");
    }
}
