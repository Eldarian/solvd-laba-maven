package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.database.SQLConnector;
import com.eldarian.solvdelivery.database.dto.OrderDto;

import java.sql.*;

public class OrderDaoImpl implements OrderDao {

    public void printAllOrders() {
        String getAllString = "SELECT * FROM orders";
        try (Connection conn = SQLConnector.connect()){
            PreparedStatement statement = conn.prepareStatement(getAllString);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getInt("dish"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderDto getOrderById(int id) {
        OrderDto order = null;
        try (Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM orders WHERE id=" + id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = extractOrderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    private OrderDto extractOrderFromResultSet(ResultSet resultSet) throws SQLException {
        OrderDto order = new OrderDto();
        order.setId(resultSet.getInt("id"));
        order.setStreet(resultSet.getString("street"));
        order.setBuildingNumber(resultSet.getInt("building_number"));
        order.setDishId(resultSet.getInt("dish"));
        order.setRestaurantId(resultSet.getInt("restaurant"));
        return order;
    }

    @Override
    public boolean insertOrder(OrderDto order) {
        try (Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO orders ('id',  'dish', 'restaurant', 'building_number', 'street') VALUES (?, ?. ?, ?, ?)");
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getDishId());
            statement.setInt(3, order.getRestaurantId());
            statement.setInt(4, order.getBuildingNumber());
            statement.setString(5, order.getStreet());
            int i = statement.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateOrder(OrderDto order) {
        try (Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE orders SET dish=?, restaurant=?, building_number=?, street=? WHERE id=?");
            statement.setInt(1, order.getDishId());
            statement.setInt(2, order.getRestaurantId());
            statement.setInt(3, order.getBuildingNumber());
            statement.setString(4, order.getStreet());
            statement.setInt(5, order.getId());
            int i = statement.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteOrder(int id) {
        try (Connection conn = SQLConnector.connect()){
            Statement statement = conn.createStatement();
            int i = statement.executeUpdate("DELETE FROM orders WHERE id=" + id);
            if(i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
