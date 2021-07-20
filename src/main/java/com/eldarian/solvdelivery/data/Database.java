package com.eldarian.solvdelivery.data;

import com.eldarian.solvdelivery.city.Building;
import com.eldarian.solvdelivery.city.Restaurant;
import com.eldarian.solvdelivery.city.Street;
import com.eldarian.solvdelivery.ordering.Dish;
import com.eldarian.solvdelivery.services.CityService;
import com.eldarian.solvdelivery.staff.Manager;
import com.eldarian.solvdelivery.staff.contact.PhoneOperator;
import com.eldarian.solvdelivery.staff.contact.WebOperator;
import com.eldarian.solvdelivery.staff.delivery.AutoCourier;
import com.eldarian.solvdelivery.staff.delivery.FootCourier;

import java.util.*;

//temporary class, will be replaced with sql or smth.
public class Database implements CityService {
    private List<Manager> managers;
    private List<Restaurant> restaurants;
    private Map<String, Street> streets;

    private static Database instance;

    private Database() {
        initCity();
        initStaff();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    //hardcoded method with data
    private void initStaff() {
        managers = new ArrayList<>();
        Manager manager = new Manager(this);
        manager.addCourier(new FootCourier("Benny"));
        manager.addCourier(new AutoCourier("Jack"));
        manager.addOperator(new PhoneOperator(manager));
        manager.addOperator(new WebOperator(manager));
        managers.add(manager);
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

        Restaurant perezmen = new Restaurant(kalesnikava, 7, "Perezmen", perezmenMenu);
        Restaurant freedomster = new Restaurant(kalinouski, 3, "Freedomster", freedomsterMenu);
        Restaurant meating = new Restaurant(skaryna, 9, "Meating", meatingMenu);
        Restaurant emptiness = new Restaurant(skaryna, 1, "Emply", null);

        restaurants = new ArrayList<>();
        restaurants.add(perezmen);
        restaurants.add(freedomster);
        restaurants.add(meating);
        restaurants.add(emptiness);

    }

    @Override
    public Restaurant findRestaurant(String name) {
        for(Restaurant restaurant: restaurants) {
            if(restaurant.getName().equals(name)) return restaurant;
        }
        return null;
    }

    @Override
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
    @Override
    public Building findBuilding(Street street, int buildingNumber) {
        Building building = null;
        if(street == null) {
            System.out.println("Error: Invalid street");
        } else {
            building = street.getBuilding(buildingNumber);
        }
        return building;
    }

    @Override
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

    public Manager getManager() {
        return managers.get(0); //TODO replace to search of free manager or make single-manager system
    }

    @Override
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

    @Override
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
