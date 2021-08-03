package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.model.order.Dish;

public interface RestaurantService {
    Dish findDish(String name);
}
