package com.eldarian.solvdelivery.model.city;

public class Restaurant extends Building {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    public Restaurant(int id, String street, int buildingNumber, String name) {
        super(street, buildingNumber);
        this.name = name;
        this.id = id;
    }

    public Restaurant() {
        super();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printMenu() {

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name +
                ", " + getBuildingNumber();
    }
}

