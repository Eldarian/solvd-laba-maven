package com.eldarian.solvdelivery.database.DAO;

import com.eldarian.solvdelivery.database.SQLConnector;
import com.eldarian.solvdelivery.model.order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {
    public void printAllOrders() {
        Connection conn = SQLConnector.connect();
        String getAllString = "SELECT * FROM orders";
        try {
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
        return null;
    }

    @Override
    public void insertOrder(Order order) {

    }

    @Override
    public void updateOrder(Order order) {

    }
}
