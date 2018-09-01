package com.example.a160final;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by badiparvaneh on 5/1/18.
 */

public class UserGlobal extends Application {
    private User user;
    private ArrayList<Food> food;
    private Order order;

    public UserGlobal() {
        user = null;
        order = null;
        this.food = new ArrayList<>();
    }
    public UserGlobal(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Food> getCart() {
        return food;
    }

    public void setCart(ArrayList<Food> food) {
        this.food = food;
    }

    public void addToCart(Food item) {
        this.food.add(item);
    }

    public int cartSize() {
        return this.food.size();
    }

    public boolean removeFromCart(String item) {

        for (Food f : food) {
            if (f.getName().compareTo(item) == 0) {
                food.remove(f);
                return true;
            }
        }
        return false;
    }

    public void removeFromCart(int position) {
        this.food.remove(position);
    }

    public void clearCart() {
        this.food = new ArrayList<>();
    }

    public double getCartTotal() {
        double sum = 0.0;
        for (Food f : food) {
            sum += f.getPriceDouble();
        }
        return sum;
    }
}
