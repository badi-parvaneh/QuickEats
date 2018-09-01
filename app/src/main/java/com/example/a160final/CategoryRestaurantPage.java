package com.example.a160final;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by badiparvaneh on 4/24/18.
 */

//This activity is the activity which we enter after user clicks on a food category
//There will be a RecyclerView of the restaurants within that category.
public class CategoryRestaurantPage extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_rest_page);

        ImageView catImage;
        TextView catName;
        RelativeLayout layout;

        catImage = findViewById(R.id.cat_img);
        catName = findViewById(R.id.cat_name);
        layout = findViewById(R.id.cat_rest_rel);


        Bundle b = getIntent().getExtras();
        String name = "";
        int img = 0;
        if (b != null) {
            name = b.getString("catName");
            img = b.getInt("catImage");
        }
        final String categoryName = name;

        //set category name and image
        catImage.setImageResource(img);
        catName.setText(name);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference categoriesRef = database.getReference("Categories");
        Category category;
        categoriesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Category category;
                DataSnapshot ds = dataSnapshot.child(categoryName);

                ArrayList<HashMap> restuarants = (ArrayList<HashMap>) ((HashMap) ((DataSnapshot) ds).getValue()).get("restaurants");
                ArrayList<Restaurant> retrievedRestaurants = new ArrayList<>();
                for (HashMap restaurant : restuarants) {
                    String restuarantName = (String) restaurant.get("name");
                    ArrayList restaurantMenu = (ArrayList) restaurant.get("menu");
                    ArrayList<Food> menu = new ArrayList<>();

                    for (Object menuItem : restaurantMenu) {
                        HashMap menuItemHashMap = (HashMap) menuItem;
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
                    retrievedRestaurants.add(new Restaurant(restuarantName, menu));
                }

                mRestaurants = retrievedRestaurants;
                mRecyclerView = (RecyclerView) findViewById(R.id.cat_rest_rec);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(CategoryRestaurantPage.this));

                mAdapter = new CategoryRestaurantAdapter(CategoryRestaurantPage.this, mRestaurants, categoryName);
                mRecyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(CategoryRestaurantPage.this, "No Category restaurants", Toast.LENGTH_LONG).show();
            }
        });





        mRecyclerView = (RecyclerView) findViewById(R.id.cat_rest_rec);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CategoryRestaurantAdapter(this, mRestaurants, categoryName);
        mRecyclerView.setAdapter(mAdapter);



    }

}
