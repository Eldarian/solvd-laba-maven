package com.eldarian.solvdelivery.database.DAO;

import com.eldarian.solvdelivery.database.SQLConnector;
import com.eldarian.solvdelivery.model.order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {

    public void printAllOrders() {
        String getAllString = "SELECT * FROM orders";
        try (Connection conn = SQLConnector.connect()){
            PreparedStatement getAll = conn.prepareStatement(getAllString);
            ResultSet resultSet = getAll.executeQuery();
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
    public Order getOrderById(int id) {
        Order order = null;
        try (Connection conn = SQLConnector.connect()) {
            PreparedStatement getAll = conn.prepareStatement("SELECT * FROM orders WHERE id=" + id);
            ResultSet resultSet = getAll.executeQuery();
            if (resultSet.next()) {
                order = extractOrderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    private Order extractOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setBuildingId(resultSet.getInt("building"));
        order.setDishId(resultSet.getInt("dish"));
        return order;
    }

    @Override
    public void insertOrder(Order order) {

    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void deleteOrder(Order order) {

    }
}
