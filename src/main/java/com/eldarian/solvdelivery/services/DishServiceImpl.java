package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.database.dao.DishDao;
import com.eldarian.solvdelivery.database.dao.DishDaoImpl;
import com.eldarian.solvdelivery.model.order.Dish;

import java.util.List;

public class DishServiceImpl implements DishService {
    private DishDao dishDao;

    public DishServiceImpl() {
        dishDao = new DishDaoImpl();
    }

    @Override
    public Dish findDish(String name) {
        return dishDao.getDishByName(name);
    }

    @Override
    public Dish findDish(int id) {
        return dishDao.getDishById(id);
    }

    @Override
    public List<String> getMenu(int restaurantId) {
        return dishDao.getDishNamesByRestaurant(restaurantId);
    }
}
