package com.eldarian.solvdelivery.model.city;

import java.util.Objects;

public class Building {
    private int id;
    private String streetName;
    private int buildingNumber;

    public Building() {

    }

    public Building(String streetName, int buildingNumber) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
    }

    public Building(int id, String streetName, int buildingNumber) {
        this.id = id;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public int getBuildingNumber() {
        return buildingNumber;
    }

//    public Street getStreet() {
//        return street;
//    }

    @Override
    public String toString() {
        return "Building{" +
                "street=" + streetName +
                ", buildingNumber=" + buildingNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return buildingNumber == building.buildingNumber && streetName.equals(building.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, buildingNumber);
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
