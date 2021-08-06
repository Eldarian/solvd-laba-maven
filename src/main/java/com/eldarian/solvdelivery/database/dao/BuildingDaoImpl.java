package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.database.SQLConnector;
import com.eldarian.solvdelivery.model.city.Building;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildingDaoImpl implements BuildingDao {
    @Override
    public Building findBuildingByStreetAndNumber(String streetName, int buildingNumber) {
        Building building = null;

        try (Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM buildings WHERE street=? AND building_number=?");
            statement.setString(1, streetName);
            statement.setInt(2, buildingNumber);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                building = extractBuildingFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return building;
    }

    private Building extractBuildingFromResultSet(ResultSet resultSet) throws SQLException {
        Building building = new Building();
        building.setStreetName(resultSet.getString("street"));
        building.setBuildingNumber(resultSet.getInt("building_number"));
        return building;
    }

    @Override
    public ArrayList<String> getStreetNames() {
        ArrayList<String> streets = new ArrayList<>();

        try (Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT street FROM buildings");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                streets.add(resultSet.getString("street"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return streets;
    }

    @Override
    public List<Integer> getBuildingNumbersByStreetName(String street) {
        List<Integer> numbers = new ArrayList<>();

        try (Connection conn = SQLConnector.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT building_number FROM buildings WHERE street=?");
            statement.setString(1, street);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numbers.add(resultSet.getInt("building_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numbers;
    }
}
