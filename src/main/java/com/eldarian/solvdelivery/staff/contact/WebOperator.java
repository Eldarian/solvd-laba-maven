package com.eldarian.solvdelivery.staff.contact;

import com.eldarian.solvdelivery.ordering.Order;
import com.eldarian.solvdelivery.staff.Manager;

public class WebOperator extends Operator{

    public WebOperator(Manager manager) {
        super(manager);
    }

    @Override
    public void handleClientData(String data) {
        //TODO register user
    }

    public String toString() {
        return "WebOperator{" + "id=" + getId() +
                ", name='" + getName() +
                "manager=" + getManager() +
                '}';
    }
}
