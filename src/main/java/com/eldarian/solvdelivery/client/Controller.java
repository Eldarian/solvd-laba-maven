package com.eldarian.solvdelivery.client;

import com.eldarian.solvdelivery.model.city.Building;
import com.eldarian.solvdelivery.model.city.Restaurant;
import com.eldarian.solvdelivery.model.order.Dish;
import com.eldarian.solvdelivery.model.order.Order;
import com.eldarian.solvdelivery.services.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    DishService dishService;
    CityService cityService;
    OrderService orderService;

    public Controller() {
        this.cityService = new CityServiceImpl();
        this.dishService = new DishServiceImpl();
        this.orderService = new OrderServiceImpl();
    }

    public Order generateOrder() {
        Order order = new Order();
        Restaurant restaurant;
        int attempt = 0;
        do {
            restaurant = chooseRestaurant(order);
            LOGGER.info(restaurant);
            attempt++;
        } while (restaurant == null && attempt < 10);
        chooseDish(order, restaurant, 0);
        chooseDestination(order);
        return order;
    }

    public Order getOrder(int id) {
        return orderService.getOrder(id);
    }

    public boolean saveOrder(Order order) {
        return orderService.addOrder(order);
    }

    public boolean updateOrder(Order order) {
        return orderService.updateOrder(order);
    }

    public boolean deleteOrder(int id) {
        return orderService.deleteOrder(id);
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
        Scanner scanner = new Scanner(System.in);
        Dish dish;
        LOGGER.info("Choose dish");
        LOGGER.info("Menu:");
        LOGGER.info(dishService.getMenu(restaurant.getId()));
        dish = dishService.findDish(scanner.nextLine());
        order.setDish(dish);

        if(dish == null && attempt < 3) {
            System.out.println("Wrong input, try again");
            chooseDish(order, restaurant, ++attempt);
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
        LOGGER.info("Numbers: " + cityService.getBuildingNumbersOnStreet(street));
        return cityService.findBuilding(street, scanner.nextInt());
    }

    private String chooseStreet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your address (Street name):");
        ArrayList<String> streetNames = cityService.getStreetNames();
        printStreetNames(streetNames);
        String street;
        do {
            street = scanner.nextLine();
            if(street.matches("\\d+")) {
                int index = Integer.parseInt(street);
                if(index >= 0 && index < streetNames.size()) {
                    street = streetNames.get(index);
                }
            }
        } while (!checkStreetExists(street));
        return street;
    }

    private void printStreetNames(ArrayList<String> streetNames) {
        for (int i = 0; i < streetNames.size(); i++) {
            System.out.println(i + ": " + streetNames.get(i));
        }
    }

    private boolean checkStreetExists(String string) {
        ArrayList<String> streetNames = cityService.getStreetNames();
        if(string.matches("\\d+")) {
            int index = Integer.parseInt(string);
            return index >= 0 && index < streetNames.size();
        }
        return streetNames.stream().anyMatch(street -> street.equals(string));
    }
}
