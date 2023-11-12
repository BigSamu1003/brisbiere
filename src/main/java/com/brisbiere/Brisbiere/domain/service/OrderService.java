package com.brisbiere.Brisbiere.domain.service;

import com.brisbiere.Brisbiere.domain.Order;
import com.brisbiere.Brisbiere.domain.repository.OrderRepository;
import com.brisbiere.Brisbiere.persistence.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public List<Order> getAll(){
        return orderRepository.getAll();
    }

    public Optional<List<Order>> getByClient(int userId){
        return orderRepository.getByClient(userId);
    }

    public Optional<List<Order>> getByEmail(String email){
        return orderRepository.getByEmail(email);
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public Integer getLastOrderId(){
        return this.orderRepository.getLastOrderId();
    }

}
