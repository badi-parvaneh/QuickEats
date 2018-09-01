package com.example.a160final;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderHistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    byte[] byteArray = null;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    private ArrayList<Food> mOrderHist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout_order_history);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ImageView userImageHomeScreen =navigationView.getHeaderView(0).findViewById(R.id.imageViewUser);
        FloatingActionButton userIcon = findViewById(R.id.userIconOrderHistory);


        userIcon.setImageResource(R.drawable.user_icon);

        byteArray=getIntent().getByteArrayExtra("image");
        if (byteArray!=null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            userImageHomeScreen.setImageBitmap(bitmap);
            userIcon.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
        }

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                String userName = ((UserGlobal) OrderHistoryActivity.this.getApplication()).getUser().getName();
                TextView t = findViewById(R.id.menu_username);
                t.setText(userName);
            }
        });

        DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference currentUserRef = usersReference.child(((UserGlobal) this.getApplication()).getUser().getEmail().split("@")[0]);
        currentUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap value = (HashMap) dataSnapshot.getValue();
                for (Object order : (ArrayList)value.get("orderHistory")) {
                    ArrayList cart = (ArrayList) ((HashMap) order).get("cart");
                    for (Object f : cart) {
                        HashMap menuItem = (HashMap) f;
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

                        mOrderHist.add(new Food(itemName, price, priceDouble, info, imageId));
                    }
                }


                mRecyclerView = (RecyclerView) findViewById(R.id.order_hist_rec);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(OrderHistoryActivity.this));

                mAdapter = new OrderHistoryAdapter(OrderHistoryActivity.this, mOrderHist);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.order_hist_rec);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(OrderHistoryActivity.this));

        mAdapter = new OrderHistoryAdapter(OrderHistoryActivity.this, mOrderHist);
        mRecyclerView.setAdapter(mAdapter);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.order_now) {
            //jump to order now activity
            Intent i = new Intent(this,HomeScreen.class);
            if(byteArray!=null){i.putExtra("image",byteArray);}
            startActivity(i);
        } else if (id == R.id.payment) {
            //jump to payment activity
            Intent i = new Intent(this,PaymentActivity.class);
            if(byteArray!=null){i.putExtra("image",byteArray);}
            startActivity(i);

        } else if (id == R.id.order_history) {
            //jump to order history activity
            Intent i = new Intent(OrderHistoryActivity.this,OrderHistoryActivity.class);
            if(byteArray!=null){i.putExtra("image",byteArray);}
            startActivity(i);

        } else if (id == R.id.settings) {
            //jump to settings activity
            Intent i = new Intent(this,settingActivity.class);
            if(byteArray!=null){i.putExtra("image",byteArray);}
            startActivity(i);
        }
        else if (id == R.id.check_status) {
            //jump to settings activity
            Intent i = new Intent(this,CheckStatusActivity.class);
            if(byteArray!=null){i.putExtra("image",byteArray);}
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
