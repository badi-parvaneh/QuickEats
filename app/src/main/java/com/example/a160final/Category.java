package com.example.a160final;

import java.util.ArrayList;

/**
 * Created by badiparvaneh on 4/24/18.
 */

public class Category {
    private String name;
    private int imageId;
    private ArrayList<Restaurant> restaurants;

    public Category(String name, int imageId, ArrayList<Restaurant> restaurants) {
        this.name = name;
        this.imageId = imageId;
        this.restaurants = restaurants;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
}
