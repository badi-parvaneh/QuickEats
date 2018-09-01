package com.example.a160final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CreditCardActivity extends AppCompatActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);


        final CardForm cardForm = (CardForm) findViewById(R.id.cardForm);
        TextView paymentAmount = (TextView) findViewById(R.id.payment_amount);
        ((TextView) findViewById(R.id.payment_amount_holder)).setText("Add Card");
        Button payButton = (Button) findViewById(R.id.btn_pay);
        paymentAmount.setText("");
        payButton.setText("Finish");





        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {

                String holderName=cardForm.getCard().getName();
                String cardNumber=cardForm.getCard().getNumber();
                String cardExpDate=cardForm.getCard().getExpYear().toString()+cardForm.getCard().getExpMonth().toString();
                String cardCVC=cardForm.getCard().getCVC();
                String cardType=cardForm.getCard().getType();

                Payment payment=new Payment(cardNumber,cardCVC,holderName,cardExpDate,cardType);
                user=((UserGlobal) CreditCardActivity.this.getApplication()).getUser();
                user.setPayment(payment);

                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = mDatabase.getReference("Users");
                myRef=myRef.child(user.getName().toLowerCase());
                myRef.setValue(user);

                Intent intent = new Intent(CreditCardActivity.this, PaymentActivity.class);
                startActivity(intent);



            }
        });










    }
}
