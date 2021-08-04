package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.database.dao.OrderDao;
import com.eldarian.solvdelivery.database.dao.OrderDaoImpl;
import com.eldarian.solvdelivery.database.dto.OrderDto;
import com.eldarian.solvdelivery.model.order.Order;

public class OrderServiceImpl implements OrderService{
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public OrderDto getOrder(int id) {
        return orderDao.getOrderById(id);
    }
}
