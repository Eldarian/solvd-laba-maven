package com.eldarian.solvdelivery.city;

import java.util.Objects;

public class Building {
    private Street street;
    private int buildingNumber;

    private Building() {

    }

    public Building(Street street, int buildingNumber) {
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public Street getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "Building{" +
                "street=" + street.getName() +
                ", buildingNumber=" + buildingNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return buildingNumber == building.buildingNumber && street.equals(building.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, buildingNumber);
    }
}
