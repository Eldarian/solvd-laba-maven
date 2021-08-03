package com.eldarian.solvdelivery.database.DAO;

import com.eldarian.solvdelivery.model.city.Street;

import java.util.ArrayList;

public interface StreetsDao {

    Street findStreetByName(String name);

    ArrayList<String> getStreetNames();
}
