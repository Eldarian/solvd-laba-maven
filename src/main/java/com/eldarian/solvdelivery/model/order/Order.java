package com.eldarian.solvdelivery.model.order;

import com.eldarian.solvdelivery.utils.JsonExecutor;
import com.eldarian.solvdelivery.model.city.Building;
import com.eldarian.solvdelivery.model.city.Restaurant;
import org.apache.log4j.Logger;

public class Order {
    private static Logger logger = Logger.getLogger(Order.class);

    private int id;
    private Restaurant restaurant;
    private Dish dish;
    private Building building;

    @Override
    public String toString() {
        return "\nid=" + id +
                ", \nrestaurant=" + restaurant +
                ", \ndish=" + dish +
                ", \ndeliver to " + building;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public boolean checkIsValid() {
        return restaurant != null && dish != null && building != null;
    }


    public int getId() {
        return id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Dish getDish() {
        return dish;
    }

    public Building getBuilding() {
        return building;
    }

    public void saveAsJSON() {
        logger.info("Saving to JSON...");
        JsonExecutor jsonExecutor = new JsonExecutor();
        jsonExecutor.convertPojoToJsonFile(this, "order" + id);
    }

    public static Order readFromJSON(String pathToFile) {
        logger.info("Reading from JSON...");
        JsonExecutor jsonExecutor = new JsonExecutor();
        return jsonExecutor.readOrderPojoFromJsonFile(pathToFile);
    }
}
