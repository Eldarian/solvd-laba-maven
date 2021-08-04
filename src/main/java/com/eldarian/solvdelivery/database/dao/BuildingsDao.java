package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.model.city.Building;


public interface BuildingsDao {
    Building findBuildingByStreetAndNumber(String streetName, int buildingNumber);
}
