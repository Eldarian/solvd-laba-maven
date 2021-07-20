package com.eldarian.solvdelivery.staff;

import com.eldarian.solvdelivery.ordering.Order;

public abstract class Employee {
	private int id;
	private String name;

	
	private static int lastId = 0;

	public Employee() {
		this.id = generateId();
		this.name = "<noname>";
	}

	public abstract void handleOrder(Order order);

	public abstract boolean canHandleOrder(Order order);
	
	private int generateId() {
		return ++lastId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
