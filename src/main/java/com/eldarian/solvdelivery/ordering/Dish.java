package com.eldarian.solvdelivery.ordering;

public class Dish {
    private String name;
    private int price;

    public int getPrice() {
        return price;
    }

    public Dish(String name) {
        this.name = name;
        this.price = 5;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", " + price + "$";
    }
}
