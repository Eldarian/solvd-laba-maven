package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.database.dto.OrderDto;

public interface OrderDao {
    void printAllOrders();
    OrderDto getOrderById(int id);
    boolean insertOrder(OrderDto order);
    void updateOrder(OrderDto order);
    void deleteOrder(OrderDto order);
}
