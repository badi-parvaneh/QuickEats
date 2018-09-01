package com.example.a160final;

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
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by badiparvaneh on 5/1/18.
 */

public class OrderHistoryAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private ArrayList<Food> mHistory;

    public OrderHistoryAdapter(Context mContext, ArrayList<Food> mHistory) {
        this.mContext = mContext;
        this.mHistory = mHistory;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_hist_cell, parent, false);
        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Food food = mHistory.get(position);
        ((OrderHistoryViewHolder) holder).bind(food);
    }

    @Override
    public int getItemCount() {
        return mHistory.size();
    }
}

class OrderHistoryViewHolder extends RecyclerView.ViewHolder {

    private CardView card;
    private TextView histFoodName;
    private TextView histFoodPrice;
    private TextView histFoodInfo;
    private ImageView histFoodImage;
    private int imageId;
    private String foodName;
    private String foodPrice;
    private String foodInfo;

    public OrderHistoryViewHolder(View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.hist_cell_card);
        histFoodName = itemView.findViewById(R.id.hist_food_name);
        histFoodPrice = itemView.findViewById(R.id.hist_food_price);
        histFoodInfo = itemView.findViewById(R.id.hist_food_ing);
        histFoodImage = itemView.findViewById(R.id.hist_food_image);

    }

    void bind (Food food) {
        foodName = food.getName();
        foodPrice = food.getPrice();
        foodInfo = food.getInfo();
        imageId = food.getImageId();

        histFoodImage.setImageResource(imageId);
        histFoodInfo.setText(foodInfo);
        histFoodName.setText(foodName);
        histFoodPrice.setText(foodPrice);

    }
}

