package com.eldarian.solvdelivery.model.staff.delivery;

import com.eldarian.solvdelivery.model.order.Order;

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
