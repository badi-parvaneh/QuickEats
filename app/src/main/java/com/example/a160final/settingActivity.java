package com.example.a160final;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


//Setting activiy. Functions: change user image, change user.
// Change user imge putextra bitmap to homeScreenMenu and set the bitmap as image for the user on that activy
//change user starts the log in page
public class settingActivity extends AppCompatActivity {
    private int imgRequest = 1;
    ImageView userImageTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_setting);



        Button changeImageButton=findViewById(R.id.changeImage);
        ImageView userImageHomeScreen = findViewById(R.id.userImageTest);
        final byte[] byteArray = getIntent().getByteArrayExtra("image");
        if (byteArray!=null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            userImageHomeScreen.setImageBitmap(bitmap);
        }

        changeImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose Picture"), imgRequest);
            }
        });
        Button changeUserButton=findViewById(R.id.logOut);
        changeUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settingActivity.this, LogIn.class);
                startActivity(intent);
            }
        });

    }
    //reference: http://codetheory.in/android-pick-select-image-from-gallery-with-intents/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == imgRequest && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                ImageView userImageHomeScreen = findViewById(R.id.userImageTest);
                userImageHomeScreen.setImageBitmap(bitmap);
                bitmap=Bitmap.createScaledBitmap(bitmap, 500, 500, true);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Intent in1 = new Intent(this, HomeScreen.class);
                in1.putExtra("image",byteArray);
                startActivity(in1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
