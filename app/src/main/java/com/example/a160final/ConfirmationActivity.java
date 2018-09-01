package com.example.a160final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Button close = findViewById(R.id.close_confirm);
        TextView confrim_message = findViewById(R.id.confirm_message);

        Bundle b = getIntent().getExtras();

        String message = "You have successfully placed an order with: ";
        String restName = "";
        if (b != null) {
            restName = b.getString("rest_name");
        }

        message += restName;

        confrim_message.setText(message);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfirmationActivity.this, HomeScreen.class));
            }
        });
    }
}
