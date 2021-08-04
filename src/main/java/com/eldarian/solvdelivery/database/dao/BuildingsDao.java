package com.eldarian.solvdelivery.database.DAO;

import com.eldarian.solvdelivery.model.city.Building;
import com.eldarian.solvdelivery.model.city.Street;

public interface BuildingsDao {
    Building findBuildingByStreetAndNumber(Street street, int buildingNumber);
}
