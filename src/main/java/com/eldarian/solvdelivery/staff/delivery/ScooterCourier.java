package com.eldarian.solvdelivery.staff.delivery;

import com.eldarian.solvdelivery.ordering.Order;

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
