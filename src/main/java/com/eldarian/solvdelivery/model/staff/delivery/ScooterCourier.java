package com.eldarian.solvdelivery.model.staff.delivery;

import com.eldarian.solvdelivery.model.order.Order;

public class ScooterCourier extends AutoCourier {

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Delivered by " + getName() + " by scooter");
    }

    @Override
    public String toString() {
        return super.toString() + ", scooter";
    }
}
