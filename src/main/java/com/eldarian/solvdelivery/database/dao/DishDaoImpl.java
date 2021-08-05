package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.database.SQLConnector;
import com.eldarian.solvdelivery.model.city.Restaurant;
import com.eldarian.solvdelivery.model.order.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDaoImpl implements DishDao{

    @Override
    public Dish getDishByName(String name) {
        Dish dish = null;

        try(Connection conn = SQLConnector.connect()) {
            PreparedStatement getByName = conn.prepareStatement("SELECT * FROM restaurants WHERE name=?");
            getByName.setString(1, name);

            ResultSet resultSet = getByName.executeQuery();
            if(resultSet.next()) {
                dish = extractDishFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }

    private Dish extractDishFromResultSet(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setId(resultSet.getInt("id"));
        dish.setName(resultSet.getString("name"));
        dish.setPrice(resultSet.getInt("price"));
        return dish;

    }

    @Override
    public List<String> getDishNamesByRestaurant(int restaurantId) {
        List<String> restaurants = new ArrayList<>();

        try(Connection conn = SQLConnector.connect()) {
            PreparedStatement getByName = conn.prepareStatement("SELECT DISTINCT name FROM dish WHERE 'restaurant'=?");
            getByName.setInt(1, restaurantId);
            ResultSet resultSet = getByName.executeQuery();
            while(resultSet.next()) {
                restaurants.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }
}
