package com.eldarian.solvdelivery.database.dao;

import com.eldarian.solvdelivery.database.SQLConnector;
import com.eldarian.solvdelivery.model.city.Building;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuildingDaoImpl implements BuildingDao {
    @Override
    public Building findBuildingByStreetAndNumber(String streetName, int buildingNumber) {
        Building building = null;

        try (Connection conn = SQLConnector.connect()) {
            PreparedStatement getByStreetAndNumber = conn.prepareStatement("SELECT * FROM buildings WHERE street=? AND building=?");
            getByStreetAndNumber.setString(1, streetName);
            getByStreetAndNumber.setInt(2, buildingNumber);

            ResultSet resultSet = getByStreetAndNumber.executeQuery();
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
        building.setId(resultSet.getInt("id"));
        building.setStreetName(resultSet.getString("street"));
        building.setBuildingNumber(resultSet.getInt("building"));
        return building;
    }

    @Override
    public ArrayList<String> getStreetNames() {
        ArrayList<String> streets = new ArrayList<>();

        try (Connection conn = SQLConnector.connect()) {
            PreparedStatement getByName = conn.prepareStatement("SELECT DISTINCT street FROM building");

            ResultSet resultSet = getByName.executeQuery();
            while (resultSet.next()) {
                streets.add(resultSet.getString("street"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return streets;
    }
}
