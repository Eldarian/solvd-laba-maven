package com.eldarian.solvdelivery.model.city;

import com.eldarian.solvdelivery.model.order.Dish;
import com.eldarian.solvdelivery.services.RestaurantService;

import java.util.List;

public class Restaurant extends Building implements RestaurantService {
    private String name;
    private List<Dish> menu;

    public Restaurant(String street, int buildingNumber, String name, List<Dish> menu) {
        super(street, buildingNumber);
        this.name = name;
        this.menu = menu;
    }

    public Restaurant(){
        super();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenu(List<Dish> menu) {
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
        return name +
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