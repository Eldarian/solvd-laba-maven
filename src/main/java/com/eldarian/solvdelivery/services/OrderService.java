package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.model.order.Order;

public interface OrderService {
    Order getOrder(int id);
    boolean addOrder(Order order);
    boolean updateOrder(Order order);
    boolean deleteOrder(int id);
}
