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

    private static Logger logger = Logger.getLogger(RestaurantDaoImpl.class);

    @Override
    public Restaurant getRestaurantByName(String name) {
        Restaurant restaurant = null;

        try(Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM restaurants WHERE name=?");
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
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM restaurants WHERE id=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                restaurant = extractRestaurantFromResultSet(resultSet);
            }
            if (restaurant == null) {
                logger.warn("Restaurant has not found");
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
            PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT name FROM restaurants");

            ResultSet resultSet = statement.executeQuery();
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
