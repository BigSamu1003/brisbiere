package com.brisbiere.Brisbiere.domain.repository;

import com.brisbiere.Brisbiere.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> getAll();
    Optional<List<Order>> getByClient(int userId);
    Optional<List<Order>> getByEmail(String email);
    Order save(Order order);
    Integer getLastOrderId();
    Order getLastOrder(String email);
}
