package com.eldarian.solvdelivery.client;

import com.eldarian.solvdelivery.model.order.Order;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Client {
    private static Logger logger = Logger.getLogger(Client.class);

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
        logger.info(order);
        order.saveAsJSON();
        controller.saveOrder(order);
    }

    public void updateOrder() {
        Order order = controller.generateOrder();
        logger.info("Choose order id to update:");
        Scanner scanner = new Scanner(System.in);
        order.setId(scanner.nextInt());
        boolean isSuccess = controller.updateOrder(order);
        logger.info(isSuccess?"update successful":"update failed");
    }

    public void checkOrder() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter order id to check:");
        logger.info(controller.getOrder(scanner.nextInt()));
    }

    public void deleteOrder() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter order id to delete:");

        boolean isSuccess = controller.deleteOrder(scanner.nextInt());
        logger.info(isSuccess?"deletion successful":"deletion failed");
    }

    public void sendOrderFromJSON() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter json name/address:");
        Order order = Order.readFromJSON(scanner.nextLine());
        controller.saveOrder(order);
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
