package com.eldarian.solvdelivery.staff.delivery;

import com.eldarian.solvdelivery.ordering.Order;

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
