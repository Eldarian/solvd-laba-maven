package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.database.dao.DishesDao;
import com.eldarian.solvdelivery.model.order.Dish;

import java.util.List;

public class DishServiceImpl implements DishService {
    private DishesDao dishesDao;

    @Override
    public Dish findDish(String name) {
        return dishesDao.getDishByName(name);
    }

    @Override
    public List<String> getMenu(int restaurantId) {
        return dishesDao.getDishNamesByRestaurant(restaurantId);
    }
}
