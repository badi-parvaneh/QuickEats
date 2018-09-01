package com.example.a160final;

import java.util.ArrayList;

/**
 * Created by badiparvaneh on 4/17/18.
 */

public class User {
    private String name;
    private Payment payment;
    private ArrayList<Order> orderHistory;
    private Order order;
    private Status orderStatus;
    private String password;
    private String email;

    public User(String name, Payment payment, ArrayList<Order> orderHistory, Order order, String password, String email) {
        this.name = name;
        this.payment = payment;
        this.order = order;
        this.password = password;
        this.email = email;
        this.orderStatus = null;
        if (orderHistory == null) {
            this.orderHistory = new ArrayList<>();
        } else {
            this.orderHistory = orderHistory;
        }
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Status getOrderStatus() { return orderStatus; }

    public String getName() {
        return name;
    }
    public Order getOrder() {
        return order;
    }

    public Payment getPayment() {
        return payment;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOrderHistory(ArrayList<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void addToOrderHistory(Order order) {
        this.orderHistory.add(order);
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }
}
