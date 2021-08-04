package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.database.dao.BuildingDao;
import com.eldarian.solvdelivery.database.dao.BuildingDaoImpl;
import com.eldarian.solvdelivery.database.dao.RestaurantDao;
import com.eldarian.solvdelivery.database.dao.RestaurantDaoImpl;
import com.eldarian.solvdelivery.model.city.Building;
import com.eldarian.solvdelivery.model.city.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class CityServiceImpl implements CityService{

    private RestaurantDao restaurantDao;
    private BuildingDao buildingDao;


    @Override
    public Restaurant findRestaurant(String name) {

        return restaurantDao.getRestaurantByName(name);
    }

    @Override
    public Restaurant findRestaurant(int id) {
        return restaurantDao.getRestaurantById(id);
    }

    @Override
    public Building findBuilding(String streetName, int buildingNumber) {
        return buildingDao.findBuildingByStreetAndNumber(streetName, buildingNumber);
    }

    @Override
    public List<String> getRestaurantNames() {
        return restaurantDao.getRestaurantNames();
    }

    @Override
    public ArrayList<String> getStreetNames() {
        return buildingDao.getStreetNames();
    }
}
