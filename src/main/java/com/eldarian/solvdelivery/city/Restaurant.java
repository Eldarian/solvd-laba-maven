package com.eldarian.solvdelivery.city;

import com.eldarian.solvdelivery.ordering.Dish;
import com.eldarian.solvdelivery.services.RestaurantService;

import java.util.List;

public class Restaurant extends Building implements RestaurantService {
    private String name;
    private List<Dish> menu;

    public Restaurant(Street street, int buildingNumber, String name, List<Dish> menu) {
        super(street, buildingNumber);
        this.name = name;
        this.menu = menu;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void printMenu() {
        if(menu != null && !menu.isEmpty()) {
            for (Dish dish : menu) {
                System.out.println(dish.getName());
            }
        } else {
            System.out.println("This restaurant has no menu.");
        }

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " on " + getStreet().getName() +
                ", " + getBuildingNumber();
    }

    @Override
    public Dish findDish(String name) {
        if(menu != null && !menu.isEmpty()) {
            for (Dish dish : menu) {
                if (dish.getName().equals(name)) {
                    return dish;
                }
            }
        } else {
            System.out.println("Warning: No menu in restaurant");
        }
        System.out.println("There is no dish with name " + name);
        return null; //TODO Replace with special Null-Dish object
    }
}
