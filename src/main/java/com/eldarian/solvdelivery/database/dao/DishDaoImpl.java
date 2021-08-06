package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.database.SQLConnector;
import com.eldarian.solvdelivery.model.order.Dish;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDaoImpl implements DishDao{

    private static final Logger LOGGER = Logger.getLogger(DishDaoImpl.class);

    @Override
    public Dish getDishByName(String name) {
        Dish dish = null;

        try(Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM dishes WHERE dish_name=?");
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                dish = extractDishFromResultSet(resultSet);
                LOGGER.info("id " + resultSet.getInt("dish_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }

    private Dish extractDishFromResultSet(ResultSet resultSet) throws SQLException {
        LOGGER.info("start extraction");
        Dish dish = new Dish();
        dish.setId(resultSet.getInt("dish_id"));
        dish.setName(resultSet.getString("dish_name"));
        dish.setPrice(resultSet.getInt("dish_price"));
        LOGGER.info("dish extraction finished");
        return dish;

    }

    @Override
    public List<String> getDishNamesByRestaurant(int restaurantId) {
        List<String> restaurants = new ArrayList<>();

        try(Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT dish_name FROM dishes WHERE restaurant_id=?");
            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                restaurants.add(resultSet.getString("dish_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    @Override
    public Dish getDishById(int id) {
        Dish dish = null;
        try(Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM dishes WHERE dish_id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                dish = extractDishFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }
}
