package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.model.city.Restaurant;

import java.util.List;

public interface RestaurantDao {
    Restaurant getRestaurantByName(String name);
    Restaurant getRestaurantById(int id);

    List<String> getRestaurantNames();
}
