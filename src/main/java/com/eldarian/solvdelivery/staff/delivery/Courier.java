package com.eldarian.solvdelivery.staff.delivery;

import com.eldarian.solvdelivery.ordering.Order;
import com.eldarian.solvdelivery.staff.Employee;

public abstract class Courier extends Employee {

	Order order;
	private boolean isFree = true;

	public boolean isFree() {
		return isFree;
	}

	public Courier() {
		super();
	}

	public Courier(String name) {
		super();
		this.setName(name);
	}


	@Override
	public void handleOrder(Order order) {
		isFree = false;
		deliverOrder(order); //Later will take time
		isFree = true;
	}

	@Override
	public boolean canHandleOrder(Order order) {
		return isFree;
	}

	public abstract void deliverOrder(Order order);

	@Override
	public String toString() {
		return "Courier{" +
				"order=" + order +
				", isFree=" + isFree +
				'}';
	}
}
