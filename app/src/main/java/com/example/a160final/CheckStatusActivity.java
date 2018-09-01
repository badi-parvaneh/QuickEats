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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class CheckStatusActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    byte[] byteArray = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout_check_status);

        final TextView orderStatus = findViewById(R.id.order_status);
        Button refresh_status = findViewById(R.id.refresh_status);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();


        String def = "Refresh to see your order status";
        orderStatus.setText(def);

        refresh_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserGlobal user = ((UserGlobal) CheckStatusActivity.this.getApplication());

                if (user.getOrder() == null) {
                    String noOrder = "You currently have no orders!";
                    orderStatus.setText(noOrder);
                } else {
                    DatabaseReference userRef = database.getReference().child("Users").child(user.getUser().getName());
                    userRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                            Long ETA = Long.MIN_VALUE;
                            String ETAString = "";
                            String pickupLocation = "";
                            while (iterator.hasNext()) {
                                DataSnapshot snapshot = iterator.next();
                                if (snapshot.getKey().equals("ETA")) {
                                    long time = snapshot.getValue(Long.class);
                                    long timeInMillisSinceEpoch = Calendar.getInstance().getTimeInMillis();//date.getTime();
                                    long timeInMinutesSinceEpoch = (timeInMillisSinceEpoch/1000) / 60;//timeInMillisSinceEpoch / TimeUnit.MILLISECONDS.toMinutes(timeInMillisSinceEpoch);

                                    ETA = timeInMinutesSinceEpoch - time;
                                } else if (snapshot.getKey().equals("pickuplocation")) {
                                    // Do pickup location stuff here.
                                    pickupLocation = snapshot.getValue(String.class).split(",")[0];
                                }
                            }
                            String message = "";
                            if (ETA > 0) {
                               message = "Your food is ready at pickup point #" + pickupLocation;
                            } else {
                                ETA = Math.abs(ETA);
                                ETAString = String.valueOf(ETA);
                                message = "Your food will be delivered at pickup point #" + pickupLocation + " in " + ETAString + " minutes";
                            }

                            orderStatus.setText(message);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ImageView userImageHomeScreen =navigationView.getHeaderView(0).findViewById(R.id.imageViewUser);
        FloatingActionButton userIcon = findViewById(R.id.userIcon);


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
                String userName = ((UserGlobal) CheckStatusActivity.this.getApplication()).getUser().getName();
                TextView t = findViewById(R.id.menu_username);
                t.setText(userName);
            }
        });

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
            Intent i = new Intent(this,OrderHistoryActivity.class);
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
