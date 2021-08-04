package com.eldarian.solvdelivery.model.city;

import org.apache.log4j.Logger;

import java.util.List;

public class Restaurant extends Building {
    private static Logger logger = Logger.getLogger(Restaurant.class);
    private String name;
    private List<String> menu;

    public Restaurant(String street, int buildingNumber, String name, List<String> menu) {
        super(street, buildingNumber);
        this.name = name;
        this.menu = menu;
    }

    public Restaurant() {
        super();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenu(List<String> menu) {
        this.menu = menu;
    }

    public List<String> getMenu() {
        return menu;
    }

    public void printMenu() {
        if (menu != null && !menu.isEmpty()) {
            for (String dish : menu) {
                logger.info(dish);
            }
        } else {
            logger.info("This restaurant has no menu.");
        }
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

