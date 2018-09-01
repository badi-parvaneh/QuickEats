package com.example.a160final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;


    private ArrayList<Food> mCart = new ArrayList<>();
    //private ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Button clear = findViewById(R.id.clear_cart);
        Button checkout = findViewById(R.id.checkout);
        TextView total = findViewById(R.id.total);



        String name = getIntent().getStringExtra("restName");

        double total_cost = ((UserGlobal) this.getApplication()).getCartTotal();
        mCart = ((UserGlobal) this.getApplication()).getCart();

        final String total_string = "Total: $" + String.valueOf(total_cost);
        final String restName = name;


        mRecyclerView = (RecyclerView) findViewById(R.id.cart_rec);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CartAdapter(this, mCart, ((UserGlobal) this.getApplication()));
        mRecyclerView.setAdapter(mAdapter);

        total.setText(total_string);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, HomeScreen.class);
                ((UserGlobal) CartActivity.this.getApplication()).clearCart();
                startActivity(intent);

            }
        });


        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                Bundle b = new Bundle();
                b.putParcelableArrayList("cart_checkout", mCart);
                b.putString("total_checkout", total_string);
                b.putString("restName_checkout", restName);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
