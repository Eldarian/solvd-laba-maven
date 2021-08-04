package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.database.dto.OrderDto;
import com.eldarian.solvdelivery.model.order.Order;

public interface OrderService {
    OrderDto getOrder(int id);
}
