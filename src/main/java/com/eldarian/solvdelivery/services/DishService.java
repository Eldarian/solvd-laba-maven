package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.model.order.Dish;

import java.util.List;

public interface DishService {
    Dish findDish(String name);
    List<String> getMenu(int restaurantId);
}
