package com.eldarian.solvdelivery.database.DAO;

import com.eldarian.solvdelivery.ordering.Order;

public interface OrderDao {
    void printAllOrders();
    Order getOrderById(int id);
    void insertOrder(Order order);
    void updateOrder(Order order);
}
