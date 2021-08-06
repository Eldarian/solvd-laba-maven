package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.database.SQLConnector;
import com.eldarian.solvdelivery.model.city.Restaurant;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao {

    private static final Logger LOGGER = Logger.getLogger(RestaurantDaoImpl.class);

    @Override
    public Restaurant getRestaurantByName(String name) {
        Restaurant restaurant = null;

        try(Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM restaurants WHERE restaurant_name=?");
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
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
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM restaurants WHERE restaurant_id=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                restaurant = extractRestaurantFromResultSet(resultSet);
            }
            if (restaurant == null) {
                LOGGER.warn("Restaurant has not found");
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
            PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT restaurant_name FROM restaurants");

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
               restaurants.add(resultSet.getString("restaurant_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    private Restaurant extractRestaurantFromResultSet(ResultSet resultSet) throws SQLException{
        Restaurant restaurant = new Restaurant();
        restaurant.setId(resultSet.getInt("restaurant_id"));
        restaurant.setName(resultSet.getString("restaurant_name"));
        restaurant.setStreetName(resultSet.getString("restaurant_street"));
        restaurant.setBuildingNumber(resultSet.getInt("restaurant_building"));
        return restaurant;
    }
}
