package com.logapps.treatments_donate_app.donate.ph_donates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.data.Donate_DetailsActivity;
import com.squareup.picasso.Picasso;

public class Ph_Donates_Details_Activity extends AppCompatActivity {


    TextView t__name , t__phone , t__address , tr_name , id_txt , chName , tryAddress , qunt;
    ImageView tr_img , back ;
    private FirebaseUser mCurrent_Users ;
    DatabaseReference  nameDatabase ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph__donates__details_);

        tryAddress = findViewById(R.id.try_address);
        qunt = findViewById(R.id.qntty);
        chName = findViewById(R.id.tr_name);
        t__name = findViewById(R.id.t_name);
        t__address = findViewById(R.id.t_address);
        t__phone = findViewById(R.id.t_phone);
        tr_name = findViewById(R.id.tr_name);
        tr_img = findViewById(R.id._treat_img);

        back = findViewById(R.id.back_btn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ph_Donates_Details_Activity.this , Ph_DonatesActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

//        nameDatabase = FirebaseDatabase.getInstance().getReference().child("ph_Users").child(mCurrent_Users.getUid());

        try {

            //address is treatment name .. details is name of pharmacy
            String Dname  = getIntent().getStringExtra("donate_prize");
            String tName = getIntent().getStringExtra("donate_prize");
            String addresss = getIntent().getStringExtra("details");
            String phoneN = getIntent().getStringExtra("donate_call");

            String qnt = getIntent().getStringExtra("donate_prize");

            String try_address = getIntent().getStringExtra("donate_address");
            String userId = getIntent().getStringExtra("id");
            String getImage = getIntent().getStringExtra("exc_image");


            Picasso.with(Ph_Donates_Details_Activity.this).load(getImage).placeholder(R.drawable.imgbg).into(tr_img);

            t__name.setText(Dname);
            t__phone.setText(phoneN);
            tr_name.setText(tName);
            t__address.setText(addresss);
            tryAddress.setText(try_address);
            id_txt.setText(userId);

            qunt.setText(Dname);

        }
        catch (Exception e)
        {

        }



    }
}