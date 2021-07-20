package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.ordering.Dish;

public interface RestaurantService {
    Dish findDish(String name);
}
