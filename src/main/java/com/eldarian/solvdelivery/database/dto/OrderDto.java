package com.eldarian.solvdelivery.database.dto;

public class OrderDto {
    private int id;
    private String street;
    private int buildingNumber;
    private int dishId;
    private int restaurantId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", dishId=" + dishId +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
