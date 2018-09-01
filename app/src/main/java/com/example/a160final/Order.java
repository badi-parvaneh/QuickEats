package com.example.a160final;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by badiparvaneh on 4/17/18.
 */

public class Order {
    private ArrayList<Food> cart;
    private Date date;
    private PickupPoint pickupPoint;
    private String username;

    public Order(ArrayList<Food> cart, Date date, PickupPoint pickupPoint, String username) {
        this.cart = cart;
        this.date = date;
        this.pickupPoint = pickupPoint;
        this.username = username;
    }

    public ArrayList<Food> getCart() {
        return cart;
    }

    public Date getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public PickupPoint getPickupPoint() {
        return pickupPoint;
    }
}
