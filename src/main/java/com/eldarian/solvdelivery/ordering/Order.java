package com.eldarian.solvdelivery.ordering;

import com.eldarian.solvdelivery.utils.JsonExecutor;
import com.eldarian.solvdelivery.city.Building;
import com.eldarian.solvdelivery.city.Restaurant;
import org.apache.log4j.Logger;

public class Order {
	private static Logger logger = Logger.getLogger(Order.class);

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

	public void setId(int id) {
		this.id = id;
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

	public boolean checkIsValid() {
		return restaurant != null && dish != null && destination != null;
	}


	public int getId() {
		return id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public Dish getDish() {
		return dish;
	}

	public Building getDestination() {
		return destination;
	}

	public void saveAsJSON() {
		logger.info("Saving to JSON...");
		JsonExecutor jsonExecutor = new JsonExecutor();
		jsonExecutor.convertPojoToJsonFile(this, "order" + id);
	}

	public static Order readFromJSON(String pathToFile) {
		logger.info("Reading from JSON...");
		JsonExecutor jsonExecutor = new JsonExecutor();
		return jsonExecutor.readOrderPojoFromJsonFile(pathToFile);
	}
}
