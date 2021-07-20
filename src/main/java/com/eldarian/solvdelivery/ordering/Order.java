package com.eldarian.solvdelivery.ordering;

import com.eldarian.solvdelivery.city.Building;
import com.eldarian.solvdelivery.city.Restaurant;

public class Order {
	int id;

	private Restaurant restaurant;
	private Dish dish;
	private Building destination;

	@Override
	public String toString() {
		return "\nid=" + id +
				", \nrestaurant=" + restaurant +
				", \ndish=" + dish +
				", \ndeliver to " + destination;
	}

    public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
    }

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public void setDestination(Building destination) {
		this.destination = destination;
	}

	public boolean isValid() {
		return restaurant != null && dish != null && destination != null;
	}
}
