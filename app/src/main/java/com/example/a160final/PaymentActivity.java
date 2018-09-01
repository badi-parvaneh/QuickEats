package com.example.a160final;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    byte[] byteArray = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout_payment);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this, BillingAddressActivity.class);
                startActivity(intent);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ImageView userImageHomeScreen =navigationView.getHeaderView(0).findViewById(R.id.imageViewUser);
        FloatingActionButton userIcon = findViewById(R.id.userIconPayment);


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
                String userName = ((UserGlobal) PaymentActivity.this.getApplication()).getUser().getName();
                TextView t = findViewById(R.id.menu_username);
                t.setText(userName);
            }
        });

        final TextView t = findViewById(R.id.textView3);
        User user = ((UserGlobal) PaymentActivity.this.getApplication()).getUser();
        if(user.getPayment()!=null) {
            t.setText("Current payment card # ends: "+ user.getPayment().getSixteenDigNum().substring(12));
            t.setTextColor(Color.GREEN);
        } else {
            t.setTextColor(Color.RED);
        }
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
            Intent i = new Intent(PaymentActivity.this,OrderHistoryActivity.class);
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
