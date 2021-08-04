package com.eldarian.solvdelivery.client;

import com.eldarian.solvdelivery.model.city.Building;
import com.eldarian.solvdelivery.model.city.Restaurant;
import com.eldarian.solvdelivery.model.order.Dish;
import com.eldarian.solvdelivery.model.order.Order;
import com.eldarian.solvdelivery.services.CityService;
import com.eldarian.solvdelivery.services.CityServiceImpl;
import com.eldarian.solvdelivery.services.DishService;
import com.eldarian.solvdelivery.services.DishServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    CityService cityService;
    DishService dishService;

    public Controller() {
        this.cityService = new CityServiceImpl();
        this.dishService = new DishServiceImpl();
    }

    public Order generateOrder() {
        Order order = new Order();
        Restaurant restaurant;
        int attempt = 0;
        do {
            restaurant = chooseRestaurant(order);
            attempt++;
        } while (restaurant == null && attempt < 10);
        chooseDish(order, restaurant, 0);
        chooseDestination(order);
        return order;
    }

    private Restaurant chooseRestaurant(Order order) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose cafe, type its name or id:");
        printRestaurants();
        Restaurant restaurant = findRestaurant(scanner.nextLine()); //TODO NULL-check
        order.setRestaurant(restaurant);
        return restaurant;
    }

    private void printRestaurants() {
        for (String restaurantName : cityService.getRestaurantNames()) {
            System.out.println(restaurantName);
        }
    }

    private Restaurant findRestaurant(String string) {
        if(string.matches("\\d+")) {
            return cityService.findRestaurant(Integer.parseInt(string));
        }
        return cityService.findRestaurant(string);
    }

    private void chooseDish(Order order, Restaurant restaurant, int attempt) {
        Dish dish = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Choose your dish:");
            restaurant.printMenu();
            dish = dishService.findDish(reader.readLine());
            order.setDish(dish);
        } catch (IOException e) {
            System.out.println("Exception in input system");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(dish == null && attempt < 3) {
                System.out.println("Wrong input, try again");
                chooseDish(order, restaurant, ++attempt);
            }
        }

    }

    private void chooseDestination(Order order) {
        String street = chooseStreet();
        Building destination = getBuilding(street);
        order.setBuilding(destination);
    }

    private Building getBuilding(String street) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your address (Building number) ");//TODO add list of existing building numbers on a street
        Building destination = cityService.findBuilding(street, scanner.nextInt());
        return destination;
    }

    private String chooseStreet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your address (Street name):");
        printStreetNames();
        String street;
        do {
            street = scanner.nextLine();
        } while (checkStreetExists(street));
        return street;
    }

    private void printStreetNames() {
        ArrayList<String> streetNames = cityService.getStreetNames();
        for (int i = 0; i < streetNames.size(); i++) {
            System.out.println(i + ": " + streetNames.get(i));
        }
    }

    private boolean checkStreetExists(String string) {
        ArrayList<String> streetNames = cityService.getStreetNames();
        if(string.matches("\\d+")) {
            int index = Integer.parseInt(string);
            if(index >= 0 && index < streetNames.size()) {
                return true;
            } else {
                return false;
            }
        }
        return streetNames.stream().anyMatch(street -> street.equals(string));
    }
}