package com.eldarian.solvdelivery.client;

import com.eldarian.solvdelivery.model.order.Order;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Client {
    private static final Logger LOGGER = Logger.getLogger(Client.class);

    private Controller controller = new Controller();

    public void execute() {
        makeOrder();
        sendOrderFromJSON();
        checkOrder();
        updateOrder();
        deleteOrder();
    }

    public void makeOrder() {
        Order order = controller.generateOrder();
        LOGGER.info(order);
        controller.saveOrder(order);
        order.saveAsJSON();
    }

    public void updateOrder() {
        Order order = controller.generateOrder();
        LOGGER.info("Choose order id to update:");
        Scanner scanner = new Scanner(System.in);
        order.setId(scanner.nextInt());
        boolean isSuccess = controller.updateOrder(order);
        LOGGER.info(isSuccess?"update successful":"update failed");
    }

    public void checkOrder() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter order id to check:");
        LOGGER.info(controller.getOrder(scanner.nextInt()));
    }

    public void deleteOrder() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter order id to delete:");

        boolean isSuccess = controller.deleteOrder(scanner.nextInt());
        LOGGER.info(isSuccess?"deletion successful":"deletion failed");
    }

    public void sendOrderFromJSON() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter json name/address:");
        Order order = Order.readFromJSON(scanner.nextLine());
        if(order.getId()==0) {
            controller.saveOrder(order);
        } else {
            controller.updateOrder(order);
        }
    }

    public void sendOrderFromProperties() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("order.properties")){
            properties.load(fileInputStream);
            System.out.println(properties.getProperty("restaurant"));

            Order order = new Order();
            controller.saveOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
