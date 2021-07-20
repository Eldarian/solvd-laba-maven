package com.eldarian.solvdelivery.ordering;

import com.eldarian.solvdelivery.city.Restaurant;
import com.eldarian.solvdelivery.city.Street;
import com.eldarian.solvdelivery.data.Database;
import com.eldarian.solvdelivery.staff.Manager;
import com.eldarian.solvdelivery.staff.contact.Operator;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Client {

    public void contactOperator() {
        Manager manager = Database.getInstance().getManager();
        Operator operator = manager.provideOperator();
        if(operator == null) return;
        sendOrderFromProperties(operator);

//        Order order = operator.generateOrder();
//        operator.confirmOrder(order);
    }

    public void sendOrderFromProperties(Operator operator) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("order.properties")){
            properties.load(fileInputStream);
            System.out.println(properties.getProperty("restaurant"));

            Order order = new Order();
            Database db = Database.getInstance();
            Restaurant restaurant = db.findRestaurant(properties.getProperty("restaurant"));
            order.setRestaurant(restaurant);
            order.setDish(restaurant.findDish(properties.getProperty("dish")));
            Street street = db.findStreet(properties.getProperty("street"));
            order.setDestination(street.getBuilding(Integer.parseInt(properties.getProperty("building"))));

            operator.handleOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
