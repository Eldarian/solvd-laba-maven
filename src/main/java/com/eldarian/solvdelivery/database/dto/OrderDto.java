package com.eldarian.solvdelivery.database.dto;

public class OrderDto {
    private int id;
    private int buildingId;
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

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
}
