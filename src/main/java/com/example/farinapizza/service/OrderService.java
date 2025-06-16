package com.example.farinapizza.service;

import com.example.farinapizza.entity.Order;
import com.example.farinapizza.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Integer addOrder(Order order) {
        Order newOrder = orderRepository.save(order); // save()為UserRepository的抽象方法，但由於@EnableJpaRepositories會自動實作出來
        return newOrder.getOrder_id();
    }
}