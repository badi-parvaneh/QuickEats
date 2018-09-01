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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by badiparvaneh on 4/17/18.
 */

public class CategoryRestaurantAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Restaurant> mRestaurants;
    private String catName;

    public CategoryRestaurantAdapter(Context mContext, ArrayList<Restaurant> mRestaurants, String catName) {
        this.mContext = mContext;
        this.mRestaurants = mRestaurants;
        this.catName = catName;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_rest_cell, parent, false);
        return new CategoryRestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Restaurant restaurant = mRestaurants.get(position);
        ((CategoryRestaurantViewHolder) holder).bind(restaurant, catName);
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }
}

class CategoryRestaurantViewHolder extends RecyclerView.ViewHolder {
    //add fields and UI elements
    private ImageView categoryImage;
    private TextView restName;
    private CardView restuarantCard;
    private String restaurantName;

    private String categoryNameString;



    public CategoryRestaurantViewHolder(View itemView) {
        super(itemView);

        //hook up UI elements
        categoryImage = itemView.findViewById(R.id.cat_img);
        restName = itemView.findViewById(R.id.cat_rest_name);
        restuarantCard = itemView.findViewById(R.id.cat_rest_card);
        restuarantCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jump to restaurant activity
                Intent intent = new Intent(view.getContext(), RestaurantPage.class);
                Bundle b = new Bundle();
                b.putString("restName", restaurantName);
                b.putString("categoryNameString", categoryNameString);
                intent.putExtras(b);
                view.getContext().startActivity(intent);

            }
        });

    }

    void bind (Restaurant restaurant, String catName) {
        //bind the stuff we need
        restaurantName = restaurant.getName();
        restName.setText(restaurantName);
        categoryNameString = catName;
    }
}
