package com.eldarian.solvdelivery.client;

import com.eldarian.solvdelivery.model.city.Restaurant;
import com.eldarian.solvdelivery.model.city.Street;
import com.eldarian.solvdelivery.services.CityServiceEmu;
import com.eldarian.solvdelivery.services.StaffServiceEmu;
import com.eldarian.solvdelivery.model.order.Order;
import com.eldarian.solvdelivery.model.staff.Manager;
import com.eldarian.solvdelivery.model.staff.contact.Operator;
import com.eldarian.solvdelivery.model.staff.contact.WebOperator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Client {

    public void contactOperator() {
        Manager manager = StaffServiceEmu.getInstance().getManager();
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
            CityServiceEmu db = CityServiceEmu.getInstance();
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
