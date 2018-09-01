package com.example.a160final;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by badiparvaneh on 4/17/18.
 */

public class HomeScreenAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Category> mCategories;

    public HomeScreenAdapter(Context mContext, ArrayList<Category> mCategories) {
        this.mContext = mContext;
        this.mCategories = mCategories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_screen_cell, parent, false);
        return new HomeScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Category category = mCategories.get(position);
        ((HomeScreenViewHolder) holder).bind(category);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }
}

class HomeScreenViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout card_view;
    //private LinearLayout card_linear;
    private CardView card;
    public ImageView picture;
    public TextView restaurant;
    public String categoryName;
    public int categoryImage;


    public HomeScreenViewHolder(View itemView) {
        super(itemView);
        card_view = itemView.findViewById(R.id.home_cell_relative);
        //card_linear = itemView.findViewById(R.id.home_cell_linear);
        card = itemView.findViewById(R.id.home_cell_card);
        picture = itemView.findViewById(R.id.home_cell_image);
        restaurant = itemView.findViewById(R.id.home_cell_name);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CategoryRestaurantPage.class);
                Bundle b = new Bundle();
                b.putString("catName", categoryName);
                b.putInt("catImage", categoryImage);
                intent.putExtras(b);
                view.getContext().startActivity(intent);
            }
        });

    }

    void bind (Category category) {
        this.categoryName = category.getName();
        this.categoryImage = category.getImageId();
        restaurant.setText(categoryName);
        picture.setImageResource(categoryImage);
    }
}
