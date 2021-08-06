package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.model.order.Order;

public interface OrderDao {
    void printAllOrders();
    Order getOrderById(int id);
    boolean insertOrder(Order order);
    boolean updateOrder(Order order);
    boolean deleteOrder(int id);

}
