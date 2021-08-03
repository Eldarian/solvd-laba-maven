package com.eldarian.solvdelivery.database.DAO;

import com.eldarian.solvdelivery.model.city.Restaurant;

import java.util.List;

public interface RestaurantsDao {
    Restaurant getRestaurantByName(String name);
    Restaurant getRestaurantById(int id);

    List<String> getRestaurantNames();
}
