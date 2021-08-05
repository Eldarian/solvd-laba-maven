package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.database.SQLConnector;
import com.eldarian.solvdelivery.database.dto.OrderDto;
import com.eldarian.solvdelivery.model.order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        order.setBuildingId(resultSet.getInt("building"));
        order.setDishId(resultSet.getInt("dish"));
        order.setRestaurantId(resultSet.getInt("restaurant"));
        return order;
    }

    @Override
    public boolean insertOrder(OrderDto order) {
        try (Connection conn = SQLConnector.connect()) {
            String insertString = String.format(
                    "INSERT INTO orders ('id', 'building', 'dish', 'restaurant') VALUES (%d, %d. %d, %d)",
                    order.getId(),
                    order.getBuildingId(),
                    order.getDishId(),
                    order.getRestaurantId());
            PreparedStatement statement = conn.prepareStatement(insertString);
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
    public void updateOrder(OrderDto order) {

    }

    @Override
    public void deleteOrder(OrderDto order) {

    }
}
