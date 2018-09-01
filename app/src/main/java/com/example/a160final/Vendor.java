package com.example.a160final;

import java.util.ArrayList;

/**
 * Created by badiparvaneh on 5/1/18.
 */

public class Vendor {
    private String name;
    private ArrayList<Food> menu;
    private ArrayList<Order> currentOrders;
    private ArrayList<Order> orderHistory;


    public Vendor(String name, ArrayList<Food> menu, ArrayList<Order> currentOrders, ArrayList<Order> orderHistory) {
        this.name = name;
        this.menu = menu;
        this.currentOrders = currentOrders;
        this.orderHistory = orderHistory;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Food> getMenu() {
        return menu;
    }

    public ArrayList<Order> getCurrentOrders() {
        return currentOrders;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
}
