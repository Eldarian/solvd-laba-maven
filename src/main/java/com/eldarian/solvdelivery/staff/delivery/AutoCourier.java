package com.eldarian.solvdelivery.staff.delivery;

import com.eldarian.solvdelivery.ordering.Order;

public class AutoCourier extends Courier {
    String carName;

    public AutoCourier() {
        super();
    }

    public AutoCourier(String name) {
        super(name);
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Delivered by " + getName() + " by auto");
    }

    @Override
    public String toString() {
        return "AutoCourier{" +
                "carName='" + carName + '\'' +
                ", order=" + order +
                ", isFree=" + isFree() +
                '}';
    }
}
