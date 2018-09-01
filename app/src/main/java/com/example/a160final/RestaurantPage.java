package com.example.a160final;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by badiparvaneh on 4/18/18.
 */

public class RestaurantPage extends AppCompatActivity {

    //Once users click on a restaurant, this activity starts

    //They will restaurant's logo, name, and a recylerview of the menu items which are clickable

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<Food> mMenu = new ArrayList<>();
    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_page);

        TextView restaurantName;
        RelativeLayout layout;

        final FloatingActionButton cart;
        final TextView count;

        restaurantName = findViewById(R.id.rest_name);
        layout = findViewById(R.id.rest_relative);
        cart = findViewById(R.id.cart_fab);
        count = findViewById(R.id.count);

        final UserGlobal ug = ((UserGlobal) this.getApplication());
        Bundle b = getIntent().getExtras();
        String name = "";
        final String rName;
        final String categoryName;
        if (b != null) {
            name = b.getString("restName");
            restaurantName.setText(name);
            rName = name;
            categoryName = b.getString("categoryNameString");
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference categoriesRef = database.getReference("Categories");
            Category category;
            categoriesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Category category;
                    DataSnapshot ds= dataSnapshot.child(categoryName);
                    ArrayList<Food> menu = new ArrayList<>();
                    HashMap restHashmap = (HashMap) ds.child(rName).getValue();
                    for (HashMap restaurant : (ArrayList<HashMap>) ds.child("restaurants").getValue()) {
                        if (((String) restaurant.get("name")).compareTo(rName) == 0) {
                            ArrayList restaurantMenu = (ArrayList) restaurant.get("menu");
                            for (Object menuItem : restaurantMenu) {

                                double priceDouble = 0.0;
                                if (((HashMap) menuItem).get("priceDouble").getClass() == Double.class) {
                                    priceDouble = (double) ((HashMap) menuItem).get("priceDouble");
                                } else {
                                    priceDouble = ((Long) ((HashMap) menuItem).get("priceDouble")).doubleValue();
                                }
                                int imageId = ((Long) ((HashMap) menuItem).get("imageId")).intValue();
                                String price = (String) ((HashMap) menuItem).get("price");
                                String itemName = (String) ((HashMap) menuItem).get("name");
                                String info = (String) ((HashMap) menuItem).get("info");

                                menu.add(new Food(itemName, price, priceDouble, info, imageId));
                            }
                        }

                    }


                    Restaurant currentRestaurant =  new Restaurant(rName, menu);
                    mMenu = menu;

                    mRecyclerView = (RecyclerView) findViewById(R.id.food_items);
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(RestaurantPage.this));

                    mAdapter = new RestaurantPageAdapter(RestaurantPage.this, mMenu, rName, cart, count, ug);
                    mRecyclerView.setAdapter(mAdapter);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(RestaurantPage.this, "No restaurant menu generated", Toast.LENGTH_LONG).show();
                }
            });
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.food_items);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new RestaurantPageAdapter(this, mMenu, name, cart, count, ug);
        mRecyclerView.setAdapter(mAdapter);



    }
}
