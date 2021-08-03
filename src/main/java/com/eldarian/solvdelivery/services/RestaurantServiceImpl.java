package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.database.DAO.DishesDao;
import com.eldarian.solvdelivery.model.order.Dish;

public class RestaurantServiceImpl implements RestaurantService{
    DishesDao dishesDao;

    @Override
    public Dish findDish(String name) {
        return dishesDao.getDishByName(name);
    }
}
