package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.model.order.Dish;

public interface DishesDao {
     Dish getDishByName(String name);
}
