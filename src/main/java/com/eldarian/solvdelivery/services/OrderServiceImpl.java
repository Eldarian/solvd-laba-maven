package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.database.DAO.OrderDao;
import com.eldarian.solvdelivery.database.DAO.OrderDaoImpl;
import com.eldarian.solvdelivery.model.order.Order;

public class OrderServiceImpl implements OrderService{
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public Order getOrder(int id) {
        return orderDao.getOrderById(id);
    }
}
