package com.example.a160final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by badiparvaneh on 4/30/18.
 */

public class CartAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Food> mCart;
    private UserGlobal ug;
    private TextView deleteView;

    public CartAdapter(Context mContext, ArrayList<Food> mCart, UserGlobal ug) {
        this.mContext = mContext;
        this.mCart = mCart;
        this.ug = ug;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_cell, parent, false);
        return new CartViewHolder(view, ug);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Food food = CartAdapter.this.ug.getCart().get(position);
        ((CartViewHolder) holder).bind(food, ug);
    }

    @Override
    public int getItemCount() {
        return mCart.size();
    }
}

class CartViewHolder extends RecyclerView.ViewHolder {
    //add fields and UI elements
    ImageView food_image;
    TextView food_name;
    TextView food_price;
    TextView food_info;
    TextView delete_item;

    String foodName;
    String foodPrice;
    String foodInfo;
    int imageId;
    UserGlobal ug;


    public CartViewHolder(View itemView, UserGlobal ug) {
        super(itemView);

        //hook up UI elements
        food_image = itemView.findViewById(R.id.cart_food_image);
        food_name = itemView.findViewById(R.id.cart_food_name);
        food_price = itemView.findViewById(R.id.cart_food_price);
        food_info = itemView.findViewById(R.id.cart_food_ing);

    }

    void bind (Food food, UserGlobal ug) {
        //bind the stuff we need
        foodName = food.getName();
        foodPrice = food.getPrice();
        foodInfo = food.getInfo();
        imageId = food.getImageId();
        this.ug = ug;

        food_image.setImageResource(imageId);
        food_name.setText(foodName);
        food_info.setText(foodInfo);
        food_price.setText(foodPrice);

    }
}
