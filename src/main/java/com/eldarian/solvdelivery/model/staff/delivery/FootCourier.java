package com.eldarian.solvdelivery.model.staff.delivery;

import com.eldarian.solvdelivery.model.order.Order;

public class FootCourier extends Courier {

    public FootCourier() {
        super();
    }

    public FootCourier(String name) {
        super(name);
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("An order " + order + " has been delivered by " + getName() + " by foot");
    }

    @Override
    public String toString() {
        return super.toString() + ", foot";
    }
}
