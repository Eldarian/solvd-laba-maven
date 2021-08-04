package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.model.city.Building;

import java.util.ArrayList;


public interface BuildingDao {
    Building findBuildingByStreetAndNumber(String streetName, int buildingNumber);

    ArrayList<String> getStreetNames();
}
