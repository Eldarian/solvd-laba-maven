package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.model.order.Dish;

import java.util.List;

public interface DishDao {
    Dish getDishByName(String name);

    List<String> getDishNamesByRestaurant(int restaurantId);

    Dish getDishById(int id);
}
