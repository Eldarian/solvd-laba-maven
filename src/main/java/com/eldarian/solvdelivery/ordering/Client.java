package com.eldarian.solvdelivery.ordering;

import com.eldarian.solvdelivery.city.Restaurant;
import com.eldarian.solvdelivery.city.Street;
import com.eldarian.solvdelivery.database.DatabaseEmulator;
import com.eldarian.solvdelivery.staff.Manager;
import com.eldarian.solvdelivery.staff.contact.Operator;
import com.eldarian.solvdelivery.staff.contact.WebOperator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Client {

    public void contactOperator() {
        Manager manager = DatabaseEmulator.getInstance().getManager();
        Operator operator = manager.provideOperator();
        if(operator == null) return;
        if (operator instanceof WebOperator) {
            //sendOrderFromProperties(operator);
            sendOrderFromJSON(operator);
            return;
        }
        Order order = operator.generateOrder();
        operator.confirmOrder(order);
    }

    public void sendOrderFromProperties(Operator operator) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("order.properties")){
            properties.load(fileInputStream);
            System.out.println(properties.getProperty("restaurant"));

            Order order = new Order();
            DatabaseEmulator db = DatabaseEmulator.getInstance();
            Restaurant restaurant = db.findRestaurant(properties.getProperty("restaurant"));
            order.setId(Integer.parseInt(properties.getProperty("id")));
            order.setRestaurant(restaurant);
            order.setDish(restaurant.findDish(properties.getProperty("dish")));
            Street street = db.findStreet(properties.getProperty("street"));
            order.setDestination(street.getBuilding(Integer.parseInt(properties.getProperty("building"))));

            order.saveAsJSON();
            operator.handleOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOrderFromJSON(Operator operator) {
        Order order = Order.readFromJSON("order555");
        operator.handleOrder(order);
    }
}
