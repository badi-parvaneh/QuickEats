package com.example.a160final;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by badiparvaneh on 4/17/18.
 */


public class RestaurantPageAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Food> mFood;
    private String restName;
    private FloatingActionButton cart;
    private TextView count;
    private UserGlobal ug;


    public RestaurantPageAdapter(Context mContext, ArrayList<Food> mFood, String restName, FloatingActionButton cart, TextView count, UserGlobal ug) {
        this.mContext = mContext;
        this.mFood = mFood;
        this.restName = restName;
        this.cart = cart;
        this.count = count;
        this.ug = ug;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_page_cell, parent, false);
        return new RestaurantPageViewHolder(view, cart, count, ug);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Food food = mFood.get(position);
        ((RestaurantPageViewHolder) holder).bind(food, restName, cart, count, ug);
    }

    @Override
    public int getItemCount() {
        return mFood.size();
    }
}

class RestaurantPageViewHolder extends RecyclerView.ViewHolder {
    private RelativeLayout card_view;
    private LinearLayout card_linear;
    private CardView card;
    private FloatingActionButton cart;
    public ImageView picture;
    public TextView foodName;
    public TextView foodPrice;
    public TextView count;
    public TextView ingredients;
    public double total = 0.0;
    public int food_count = 0;
    public String foodNameString;
    public String foodPriceString;
    public String foodInfo;
    public UserGlobal userGlobal;
    public double foodPriceDouble;
    int foodImageId;
    Application app;
    Food food;
    ArrayList<Food> cartList = new ArrayList<>();
    String restName;



    public RestaurantPageViewHolder(final View itemView, FloatingActionButton cart, final TextView count, final UserGlobal ug) {
        super(itemView);
        card_view = itemView.findViewById(R.id.rest_cell_rel);
        card_linear = itemView.findViewById(R.id.rest_cell_linear);
        card = itemView.findViewById(R.id.rest_cell_card);
        picture = itemView.findViewById(R.id.food_image);
        foodName = itemView.findViewById(R.id.food_name);
        foodPrice = itemView.findViewById(R.id.food_price);
        ingredients = itemView.findViewById(R.id.food_ing);
        this.cart = cart;
        this.count = count;
        this.userGlobal = ug;

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ug.addToCart(food);
                count.setText(String.valueOf(ug.cartSize()));
                total = ug.getCartTotal();

            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CartActivity.class);
                intent.putExtra("restName", restName);
                v.getContext().startActivity(intent);

            }
        });

    }

    void bind (Food food, String restName, FloatingActionButton cart, TextView count, UserGlobal ug) {

        //bind the stuff we need
        foodInfo = food.getInfo();
        foodImageId = food.getImageId();
        foodNameString = food.getName();
        foodPriceString = food.getPrice();
        foodPriceDouble = food.getPriceDouble();
        this.restName = restName;
        this.cart = cart;
        this.count = count;
        this.userGlobal = ug;

        picture.setImageResource(foodImageId);
        foodName.setText(foodNameString);
        foodPrice.setText(foodPriceString);
        ingredients.setText(foodInfo);
        this.food = new Food(foodNameString, foodPriceString, foodPriceDouble, foodInfo, foodImageId);
    }
}
