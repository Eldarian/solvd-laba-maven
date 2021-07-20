package com.eldarian.solvdelivery.city;

import java.util.ArrayList;
import java.util.List;

public class Street {
    private List<Building> buildings;
    private String name;

    public Street(String name, int buildingCount) {
        this.name = name;
        buildings = new ArrayList<>();
        for (int i = 1; i <= buildingCount; i++) {
            buildings.add(new Building(this, i));
        }
    }

    public String getName() {
        return name;
    }

    public int getBuildingCount() {
        if(buildings != null) { //QUESTION list of buildings initialized in constructor. Should I make null-check?
            return buildings.size();
        }
        System.out.println("Error: Street is empty");
        return 0;
    }

    public Building getBuilding(int buildingNumber) {
        if(buildingNumber <= buildings.size() && buildingNumber > 0) {
            Building building = buildings.get(buildingNumber - 1);
            if(building == null) {
                System.out.println("Error: Building has not been initialized");
            }
            return building;
        }
        System.out.println("Error: Invalid building number");
        return null;
    }

    @Override
    public String toString() {
        return "Street{" +
                "buildingsCount=" + buildings.size() +
                ", name='" + name + '\'' +
                '}';
    }
}
