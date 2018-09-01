package com.example.a160final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference vendorRef = database.getReference().child("Vendors");

        Button place_order = findViewById(R.id.place_order);
        Button cancel_order = findViewById(R.id.cancel_order);

        TextView total = findViewById(R.id.total_checkout);

        Bundle b = getIntent().getExtras();

        String total_string = "";
        ArrayList<Food> cart = new ArrayList<>();
        String rest_name = "";
        if (b != null) {
            total_string = b.getString("total_checkout");
            cart = b.getParcelableArrayList("cart_checkout");
            rest_name = b.getString("restName_checkout");
        }

        total.setText(total_string);

        final String restName = rest_name;

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final UserGlobal currentUser = ((UserGlobal) CheckoutActivity.this.getApplication());
                if (currentUser.getUser().getPayment() == null) {
                    Toast.makeText(CheckoutActivity.this, "Please setup a payment method from Menu->Payment.", Toast.LENGTH_LONG).show();
                } else {
                    DatabaseReference vendorRestRef = vendorRef.child(restName);

                    database.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            DatabaseReference vendors = database.getReference("Vendors");
                            Order order = new Order(currentUser.getCart(), new Date(), null, currentUser.getUser().getName());
                            vendors.child(restName).child("currentOrders").setValue(order);
                            ((UserGlobal) CheckoutActivity.this.getApplication()).setOrder(order);
                            DatabaseReference users = database.getReference("Users");
                            currentUser.getUser().addToOrderHistory(order);
                            users.child(currentUser.getUser().getEmail().split("@")[0]).setValue(currentUser.getUser());
                            currentUser.clearCart();

                            Intent intent = new Intent(CheckoutActivity.this, ConfirmationActivity.class);
                            Bundle b = new Bundle();
                            b.putString("rest_name", restName);
                            intent.putExtras(b);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }
        });

        cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckoutActivity.this, RestaurantPage.class));
            }
        });

    }
}
