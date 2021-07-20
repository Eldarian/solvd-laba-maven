package com.eldarian.solvdelivery.staff.contact;

import com.eldarian.solvdelivery.ordering.Dish;
import com.eldarian.solvdelivery.ordering.Order;
import com.eldarian.solvdelivery.city.Building;
import com.eldarian.solvdelivery.city.Restaurant;
import com.eldarian.solvdelivery.city.Street;
import com.eldarian.solvdelivery.services.CityService;
import com.eldarian.solvdelivery.staff.Employee;
import com.eldarian.solvdelivery.staff.Manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public abstract class Operator extends Employee {
    private Manager manager;
    private CityService cityService;

    public Operator(Manager manager) {
        super();
        this.manager = manager;
        manager.addOperator(this);
        cityService = manager.getCityService();
    }

    public abstract void handleClientData(String data);

    @Override
    public void handleOrder(Order order) {
        manager.handleOrder(order);
    }

    @Override
    public boolean canHandleOrder(Order order) {
        if(order.isValid()) {
            return manager.canHandleOrder(order);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Operator{" + "id=" + getId() +
                ", name='" + getName() +
                "manager=" + manager +
                '}';
    }

    private Restaurant findRestaurant(String string) {
        if(string.matches("\\d+")) {
            return cityService.findRestaurant(Integer.parseInt(string));
        }
        return cityService.findRestaurant(string);
    }

    private Building findBuilding(Street street, int number) {
        return cityService.findBuilding(street, number);
    }

    private Street findStreet(String string) {
        if(string.matches("\\d+")) {
            ArrayList<String> streetNames = cityService.getStreetNames();
            int index = Integer.parseInt(string);
            if(index >= 0 && index < streetNames.size()) {
                return cityService.findStreet(streetNames.get(index));
            } else {
                return null;
            }

        }
        return cityService.findStreet(string);
    }

    public Manager getManager() {
        return manager;
    }

    private void printRestaurants() {
        for (String restaurantName : cityService.getRestaurantNames()) {
            System.out.println(restaurantName);
        }
    }

    private void printStreetNames() {
        ArrayList<String> streetNames = cityService.getStreetNames();
        for (int i = 0; i < streetNames.size(); i++) {
            System.out.println(i + ": " + streetNames.get(i));
        }
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

    private void chooseDish(Order order, Restaurant restaurant, int attempt) {
        Dish dish = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Choose your dish:");
            restaurant.printMenu();
            dish = restaurant.findDish(reader.readLine());
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

    private Restaurant chooseRestaurant(Order order) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose cafe, type its name or id:");
        printRestaurants();
        Restaurant restaurant = findRestaurant(scanner.nextLine()); //TODO NULL-check
        order.setRestaurant(restaurant);
        return restaurant;
    }

    private void chooseDestination(Order order) {
        Street street = chooseStreet();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your address (Building number) " + street.getBuildingCount() + " buildings total:");
        Building destination = findBuilding(street, scanner.nextInt());
        order.setDestination(destination);
    }

    private Street chooseStreet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your address (Street name):");
        printStreetNames();
        Street street = findStreet(scanner.nextLine());
        return street;
    }

    public void confirmOrder(Order order) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Confirm your order, [y]es or [n]o: " + order);
        boolean isCorrect = false;
        do {
            String line = scanner.nextLine();
            switch (line) {
                case "yes":
                case "y":
                    if(canHandleOrder(order)) {
                        System.out.println("Your order has been sent to delivery service");
                        handleOrder(order);
                    } else {
                        System.out.println("Your order is incorrect.");
                    }
                    isCorrect = true;
                    break;
                case "no":
                case "n":
                    System.out.println("Try again!");
                    isCorrect = true;
                    break;
                default:
                    System.out.println("Incorrect type, try again.");
                    break;
            }

        } while (!isCorrect);
    }
}

