package com.logapps.treatments_donate_app;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.logapps.treatments_donate_app.Person.Person_login_activity;
import com.logapps.treatments_donate_app.Pharmacy.Ph_login_activity;
import com.logapps.treatments_donate_app.donate.Donate_login_activity;


public class MainActivity extends AppCompatActivity {

    //vars
    CardView pharma , person , donate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        pharma = findViewById(R.id.pharmacy);
        person = findViewById(R.id.person);
        donate = findViewById(R.id.donate);

        //click on pharmacy card
        pharma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //go to pharmacy login
                Intent i = new Intent(MainActivity.this , Ph_login_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });

        //click on person card
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to person login
                Intent i = new Intent(MainActivity.this , Person_login_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        //click on charity card
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to charity login
                Intent i = new Intent(MainActivity.this , Donate_login_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }
}
