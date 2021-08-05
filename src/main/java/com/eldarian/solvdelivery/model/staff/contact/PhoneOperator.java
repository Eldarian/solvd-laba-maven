package com.eldarian.solvdelivery.model.staff.contact;

import com.eldarian.solvdelivery.model.staff.Manager;

public class PhoneOperator extends Operator {

    public PhoneOperator(Manager manager) {
        super(manager);
    }

    @Override
    public void handleClientData(String data) {
        //TODO register clients phone number
    }

    @Override
    public String toString() {
        return "PhoneOperator{" + "id=" + getId() +
                ", name='" + getName() +
                "manager=" + getManager() +
                '}';
    }
}

