package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.database.SQLConnector;
import com.eldarian.solvdelivery.model.city.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao {

    @Override
    public Restaurant getRestaurantByName(String name) {
        Restaurant restaurant = null;

        try(Connection conn = SQLConnector.connect()) {
            PreparedStatement getByName = conn.prepareStatement("SELECT * FROM restaurants WHERE 'name'=?");
            getByName.setString(0, name);

            ResultSet resultSet = getByName.executeQuery();
            if(resultSet.next()) {
                restaurant = extractRestaurantFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public Restaurant getRestaurantById(int id) {
        Restaurant restaurant = null;

        try(Connection conn = SQLConnector.connect()) {
            PreparedStatement getByName = conn.prepareStatement("SELECT * FROM restaurants WHERE 'id'=?");
            getByName.setInt(0, id);

            ResultSet resultSet = getByName.executeQuery();
            if(resultSet.next()) {
                restaurant = extractRestaurantFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public List<String> getRestaurantNames() {
        List<String> restaurants = new ArrayList<>();

        try(Connection conn = SQLConnector.connect()) {
            PreparedStatement getByName = conn.prepareStatement("SELECT DISTINCT name FROM restaurants");

            ResultSet resultSet = getByName.executeQuery();
            while(resultSet.next()) {
               restaurants.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    private Restaurant extractRestaurantFromResultSet(ResultSet resultSet) throws SQLException{
        Restaurant restaurant = new Restaurant();
        restaurant.setId(resultSet.getInt("id"));
        restaurant.setName(resultSet.getString("name"));
        restaurant.setStreetName(resultSet.getString("street"));
        restaurant.setBuildingNumber(resultSet.getInt("building"));
        return restaurant;
    }
}
