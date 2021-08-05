package com.eldarian.solvdelivery.model.staff;

import com.eldarian.solvdelivery.utils.exceptions.NoEmployeeException;
import com.eldarian.solvdelivery.model.order.Order;
import com.eldarian.solvdelivery.services.CityService;
import com.eldarian.solvdelivery.model.staff.delivery.Courier;
import com.eldarian.solvdelivery.model.staff.contact.Operator;
import com.eldarian.solvdelivery.model.staff.contact.PhoneOperator;
import com.eldarian.solvdelivery.model.staff.contact.WebOperator;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Manager extends Employee {

    private static Logger logger = Logger.getLogger(Manager.class);
    private List<Courier> couriers;
    private List<Operator> operators;
    CityService cityService;

    public Manager() {
        this.couriers = new LinkedList<>();
        this.operators = new LinkedList<>();
    }

    public Manager(CityService cityService) {
        this.couriers = new LinkedList<>();
        this.operators = new LinkedList<>();
        this.cityService = cityService;
    }

    public Manager(List couriers, List operators) {
        this.couriers = couriers;
        this.operators = operators;
        this.operators = operators;
    }


    @Override
    public void handleOrder(Order order) {
        try {
            if(couriers != null) {
                for(Courier courier : couriers) {
                    if(courier.isFree()) {
                        courier.handleOrder(order);
                        return;
                    }
                }
            } else {
                throw new NoEmployeeException("Error: couriers list has not been initialized");
            }
            throw new NoEmployeeException("No couriers available");
        } catch (NoEmployeeException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            logger.info("Order has been cancelled");
        }

    }

    @Override
    public boolean canHandleOrder(Order order) {
        if(couriers == null || couriers.isEmpty()) return false;
        return true;
    }

    public void addOperator(Operator operator) {
        if(operators == null) {
            operators = new LinkedList<>();
        }
        operators.add(operator);

    }

    public void addCourier(Courier courier) {
        if(couriers == null) {
            couriers = new LinkedList<>();
        }
        couriers.add(courier);
    }

    public Operator getWebOperator() throws NoEmployeeException {
        if(operators == null || operators.isEmpty()) {
            throw new NoEmployeeException("Error with operator list");
        }
        for(Operator operator : operators) {
            if(operator instanceof WebOperator) {
                return operator;
            }
        }
        throw new NoEmployeeException("No web operators available");
    }

    public Operator getPhoneOperator() throws NoEmployeeException {
        if(operators == null || operators.isEmpty()) {
            throw new NoEmployeeException("Error with operator list");
        }
        for(Operator operator : operators) {
            if(operator instanceof PhoneOperator) {
                return operator;
            }
        }
        throw new NoEmployeeException("No phone operators available");
    }

    public CityService getCityService() {
        return cityService;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }

    public Operator provideOperator() {
        logger.info("Welcome to DelivereD! [p]hone or [w]eb?");
        Scanner scanner = new Scanner(System.in);
        Operator operator = null;
        int attempt = 0;

        do {
            String line = scanner.nextLine();
            switch (line) {
                case "phone":
                case "p":
                    try {
                        operator = getPhoneOperator();
                    } catch (NoEmployeeException e) {
                        logger.error(e.getMessage());
                        logger.info("Try another type of operator");
                    }
                    if(operator == null) {
                        logger.error("There is no available phone operator or there is error");
                        break;
                    }
                    logger.info("Called Phone Operator " + operator.getName());
                    break;
                case "web":
                case "w":
                    try {
                        operator = getWebOperator();
                    } catch (NoEmployeeException e) {
                        logger.error(e.getMessage());
                        logger.info("Try another type of operator");
                    }

                    if(operator == null) {
                        logger.error("There is no available phone operator or there is error");
                        break;
                    }
                    logger.info("Called Web Operator " + operator.getName());
                    break;
                default:
                    logger.info("Incorrect type, try again. [p]hone or [w]eb?");
                    break;
            }
            attempt++;
        } while (operator == null && attempt < 10);
        if(attempt >= 10) {
            logger.error("Error: too many attempts");
        }
        return operator;
    }
}