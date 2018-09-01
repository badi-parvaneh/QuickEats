package com.example.a160final;

import android.location.Location;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by badiparvaneh on 4/17/18.
 */

public class Restaurant {
    private String name;
    private ArrayList<Food> menu;

    public Restaurant(String name, ArrayList<Food> menu) {
        this.name = name;
        this.menu = menu;
    }


    public String getName() {
        return name;
    }

    public ArrayList<Food> getMenu() {
        return menu;
    }

}
