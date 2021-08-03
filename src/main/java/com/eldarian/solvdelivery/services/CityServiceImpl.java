package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.database.DAO.BuildingsDao;
import com.eldarian.solvdelivery.database.DAO.RestaurantsDao;
import com.eldarian.solvdelivery.database.DAO.StreetsDao;
import com.eldarian.solvdelivery.model.city.Building;
import com.eldarian.solvdelivery.model.city.Restaurant;
import com.eldarian.solvdelivery.model.city.Street;

import java.util.ArrayList;
import java.util.List;

public class CityServiceImpl implements CityService{

    private StreetsDao streetsDao;
    private RestaurantsDao restaurantsDao;
    private BuildingsDao buildingsDao;

    @Override
    public Restaurant findRestaurant(String name) {

        return restaurantsDao.getRestaurantByName(name);
    }

    @Override
    public Restaurant findRestaurant(int id) {
        return restaurantsDao.getRestaurantById(id);;
    }

    @Override
    public Building findBuilding(Street street, int buildingNumber) {
        return buildingsDao.findBuildingByStreetAndNumber(street, buildingNumber);
    }

    @Override
    public Street findStreet(String name) {
        return streetsDao.findStreetByName(name);
    }

    @Override
    public List<String> getRestaurantNames() {
        return restaurantsDao.getRestaurantNames();
    }

    @Override
    public ArrayList<String> getStreetNames() {
        return streetsDao.getStreetNames();
    }
}
