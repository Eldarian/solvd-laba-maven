package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.model.city.Building;
import com.eldarian.solvdelivery.model.city.Restaurant;
import com.eldarian.solvdelivery.model.city.Street;
import com.eldarian.solvdelivery.model.order.Dish;

import java.util.*;

//temporary class, will be replaced with sql
public class CityServiceEmu  {

    private List<Restaurant> restaurants;
    private Map<String, Street> streets;

    private static CityServiceEmu instance;

    private CityServiceEmu() {
        initCity();
    }

    public static CityServiceEmu getInstance() {
        if (instance == null) {
            instance = new CityServiceEmu();
        }
        return instance;
    }

    //hardcoded method with data
    private void initCity() {
        streets = new HashMap();
        Street kalesnikava = new Street("Kalesnikava Street", 12);
        Street kalinouski = new Street("Kalinouski Alley", 8);
        Street skaryna = new Street("Skaryna Prospect", 15);
        streets.put("Kalesnikava Street", kalesnikava);
        streets.put("Kalinouski Alley", kalinouski);
        streets.put("Skaryna Prospect", skaryna);

        List<Dish> perezmenMenu = new ArrayList<>();
        perezmenMenu.add(new Dish("pizza"));
        perezmenMenu.add(new Dish("burger"));
        perezmenMenu.add(new Dish("cola"));

        List<Dish> freedomsterMenu = new ArrayList<>();
        freedomsterMenu.add(new Dish("domster"));
        freedomsterMenu.add(new Dish("shawarma"));

        List<Dish> meatingMenu = new ArrayList<>();
        meatingMenu.add(new Dish("chicken barbeque"));
        meatingMenu.add(new Dish("meatballs"));
        meatingMenu.add(new Dish("pork kebab"));

        Restaurant perezmen = new Restaurant(kalesnikava.getName(), 7, "Perezmen", perezmenMenu);
        Restaurant freedomster = new Restaurant(kalinouski.getName(), 3, "Freedomster", freedomsterMenu);
        Restaurant meating = new Restaurant(skaryna.getName(), 9, "Meating", meatingMenu);
        Restaurant emptiness = new Restaurant(skaryna.getName(), 1, "Emply", null);

        restaurants = new ArrayList<>();
        restaurants.add(perezmen);
        restaurants.add(freedomster);
        restaurants.add(meating);
        restaurants.add(emptiness);

    }


    public Restaurant findRestaurant(String name) {
        for(Restaurant restaurant: restaurants) {
            if(restaurant.getName().equals(name)) return restaurant;
        }
        return null;
    }


    public Restaurant findRestaurant(int id) {
        if(restaurants == null) {
            System.out.println("Error: Missing restaurants list");
            return null;
        }
        if(streets.isEmpty()) {
            System.out.println("Warning: There are no restaurants in database");
            return null;
        }

        Restaurant restaurant = restaurants.get(id);
        if(restaurant == null) {
            System.out.println("Error: Invalid restaurant name");
        }
        return restaurant;
    }

    public Building findBuilding(Street street, int buildingNumber) {
        Building building = null;
        if(street == null) {
            System.out.println("Error: Invalid street");
        } else {
            building = street.getBuilding(buildingNumber);
        }
        return building;
    }


    public Street findStreet(String name) {
        if(streets == null) {
            System.out.println("Error: Missing streets map");
            return null;
        }
        if(streets.isEmpty()) {
            System.out.println("Warning: There are no streets in database");
            return null;
        }
        if(!streets.containsKey(name)) {
            System.out.println("Invalid street name");
            return null;
        }
        Street street = streets.get(name);
        if(street == null) {
            System.out.println("Error: Street is not initialized");
        }
        return street;
    }



    public List<String> getRestaurantNames() {
        List<String> list = new ArrayList<>();
        for (Restaurant restaurant: restaurants) {
            list.add(restaurants.indexOf(restaurant) + ": " + restaurant.getName());
        }
        if(list.isEmpty()) {
            System.out.println("There are no restaurants in database");
        }
        return list;
    }

    public ArrayList<String> getStreetNames() {
        if(streets == null) {
            System.out.println("Error: Missing streets map");
            return null;
        }
        if(streets.isEmpty()) {
            System.out.println("Warning: There are no streets in database");
            return null;
        }
        ArrayList<String> list = new ArrayList<>();
        for (String street: streets.keySet()) {
            list.add(street);
        }
        if(list.isEmpty()) {
            System.out.println("There are no streets in database");
        }
        return list;
    }
}
