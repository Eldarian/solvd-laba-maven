package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.database.dao.OrderDao;
import com.eldarian.solvdelivery.database.dao.OrderDaoImpl;
import com.eldarian.solvdelivery.model.order.Order;

public class OrderServiceImpl implements OrderService{
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public Order getOrder(int id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public boolean addOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }

    @Override
    public boolean deleteOrder(int id) {
        return orderDao.deleteOrder(id);
    }

}
