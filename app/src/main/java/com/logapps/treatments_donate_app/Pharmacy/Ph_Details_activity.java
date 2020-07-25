package com.logapps.treatments_donate_app.Pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.ph_donates.Ph_DonatesActivity;
import com.logapps.treatments_donate_app.donate.ph_donates.Ph_Donates_Details_Activity;
import com.squareup.picasso.Picasso;

public class Ph_Details_activity extends AppCompatActivity {


    TextView name , phone , address , em , price ;
    ImageView imageView , back ;

    Button enter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph__details_activity);

        name = findViewById(R.id.name );
        phone = findViewById(R.id.phone_number);
        address = findViewById(R.id.address);
        em = findViewById(R.id.ef_material);
        price = findViewById(R.id.price);
        back = findViewById(R.id.back_btn);

        enter = findViewById(R.id.enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ph_Details_activity.this , Ph_home_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        imageView = findViewById(R.id.final_image);

        //address is treatment name .. details is name of pharmacy
        String Dname  = getIntent().getStringExtra("details");
        String Daddress = getIntent().getStringExtra("donate_address");
        String Dphone = getIntent().getStringExtra("donate_call");

        String ef_m = getIntent().getStringExtra("em");
        String d_price = getIntent().getStringExtra("donate_prize");
        String getImage = getIntent().getStringExtra("t_image");


        Picasso.with(Ph_Details_activity.this).load(getImage).placeholder(R.drawable.imgbg).into(imageView);

        name.setText(Dname);
        address.setText(Daddress);
        phone.setText(Dphone);
        em.setText(ef_m);
        price.setText(d_price);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ph_Details_activity.this , Ph_home_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }
}