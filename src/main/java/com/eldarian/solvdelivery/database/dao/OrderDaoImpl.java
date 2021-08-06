package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.database.SQLConnector;
import com.eldarian.solvdelivery.model.city.Building;
import com.eldarian.solvdelivery.model.city.Restaurant;
import com.eldarian.solvdelivery.model.order.Dish;
import com.eldarian.solvdelivery.model.order.Order;
import org.apache.log4j.Logger;

import java.sql.*;

public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    public void printAllOrders() {
        String getAllString = "SELECT * FROM orders";
        try (Connection conn = SQLConnector.connect()){
            PreparedStatement statement = conn.prepareStatement(getAllString);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("order_id"));
                System.out.println(resultSet.getInt("dish_id"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrderById(int id) {
        Order order = null;
        try (Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement(
            "SELECT \n" +
                    "order_id,\n" +
                    "restaurants.restaurant_id,\n" +
                    "restaurants.restaurant_name,\n" +
                    "restaurants.restaurant_building,\n" +
                    "restaurants.restaurant_street,\n" +
                    "dishes.dish_id, \n" +
                    "dishes.dish_name,\n" +
                    "dishes.dish_price,\n" +
                    "orders.building_number,\n" +
                    "orders.street\n" +
                    "FROM \n" +
                    "orders INNER JOIN dishes \n" +
                    "ON orders.dish_id=dishes.dish_id \n" +
                    "INNER JOIN restaurants \n" +
                    "ON orders.restaurant_id=restaurants.restaurant_id \n" +
                    "\n" +
                    "WHERE order_id=" + id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = extractOrderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean insertOrder(Order order) {
        try (Connection conn = SQLConnector.connect()) {
            LOGGER.info(order);
            PreparedStatement statement = conn.prepareStatement("INSERT INTO orders (restaurant_id, dish_id, street, building_number) VALUES (?, ?, ?, ?) RETURNING order_id");
            statement.setInt(1, order.getRestaurant().getId());
            statement.setInt(2, order.getDish().getId());
            statement.setString(3, order.getBuilding().getStreetName());
            statement.setInt(4, order.getBuilding().getBuildingNumber());

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                order.setId(resultSet.getInt("order_id"));
                LOGGER.info("order " + order.getId() + " successfully added");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateOrder(Order order) {
        try (Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE orders SET dish_id=?, restaurant_id=?, building_number=?, street=? WHERE order_id=?");
            statement.setInt(1, order.getDish().getId());
            statement.setInt(2, order.getRestaurant().getId());
            statement.setInt(3, order.getBuilding().getBuildingNumber());
            statement.setString(4, order.getBuilding().getStreetName());
            statement.setInt(5, order.getId());
            int i = statement.executeUpdate();
            if(i == 1) {
                conn.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Order extractOrderFromResultSet(ResultSet resultSet) throws SQLException{
        Order order = new Order();
        order.setId(resultSet.getInt("order_id"));

        Restaurant restaurant = new Restaurant(
                resultSet.getInt("restaurant_id"),
                resultSet.getString("street"),
                resultSet.getInt("building_number"),
                resultSet.getString("restaurant_name"));
        order.setRestaurant(restaurant);

        Dish dish = new Dish(
                resultSet.getInt("dish_id"),
                resultSet.getString("dish_name"),
                resultSet.getInt("dish_price"));
        order.setDish(dish);

        Building building = new Building(
                resultSet.getString("street"),
                resultSet.getInt("building_number"));
        order.setBuilding(building);

        return order;
    }

    @Override
    public boolean deleteOrder(int id) {
        try (Connection conn = SQLConnector.connect()){
            Statement statement = conn.createStatement();
            int i = statement.executeUpdate("DELETE FROM orders WHERE order_id=" + id);
            if(i == 1) {
                conn.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
