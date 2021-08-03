package com.eldarian.solvdelivery.model.order;

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

    public Dish() {

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", " + price + "$";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
