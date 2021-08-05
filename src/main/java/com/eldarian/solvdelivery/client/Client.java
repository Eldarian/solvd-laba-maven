package com.eldarian.solvdelivery.client;

import com.eldarian.solvdelivery.model.order.Order;
import com.eldarian.solvdelivery.model.staff.Manager;
import com.eldarian.solvdelivery.model.staff.contact.Operator;
import com.eldarian.solvdelivery.model.staff.contact.WebOperator;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Client {
    private static Logger logger = Logger.getLogger(Client.class);

    public void contactOperator() {
        Manager manager = new Manager();
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

    public void makeOrder() {
        Controller controller = new Controller();
        Order order = controller.generateOrder();
        logger.info(order);
    }
}
