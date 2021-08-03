package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.database.DAO.OrderDao;
import com.eldarian.solvdelivery.model.order.Order;

public interface OrderService {
    Order getOrder(int id);
}
