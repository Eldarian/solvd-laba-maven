package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.model.city.Building;
import com.eldarian.solvdelivery.model.city.Restaurant;
import com.eldarian.solvdelivery.model.city.Street;

import java.util.ArrayList;
import java.util.List;

public interface CityService {
    Restaurant findRestaurant(String name);
    Restaurant findRestaurant(int id);
    Building findBuilding(Street street, int buildingNumber);
    Street findStreet(String name);

    List<String> getRestaurantNames();
    ArrayList<String> getStreetNames();



}
