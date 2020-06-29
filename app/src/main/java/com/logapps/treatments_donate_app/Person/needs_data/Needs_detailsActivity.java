package com.logapps.treatments_donate_app.Person.needs_data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.logapps.treatments_donate_app.R;
import com.squareup.picasso.Picasso;

public class Needs_detailsActivity extends AppCompatActivity {


    TextView t__name , t__phone , t__address , tr_name ;
    DatabaseReference mUserDatabase ;
    FirebaseUser uid ;
    ImageView tr_img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needs_details);

        t__name = findViewById(R.id.t_name);
        t__address = findViewById(R.id.t_address);
        t__phone = findViewById(R.id.t_phone);
        tr_name = findViewById(R.id.tr_name);
        tr_img = findViewById(R.id._proof_img);



        uid = FirebaseAuth.getInstance().getCurrentUser();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("All needs");

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String Dname  = getIntent().getStringExtra("name");
                String tName = getIntent().getStringExtra("details");
                String phoneN = getIntent().getStringExtra("donate_call");
                String addresss = getIntent().getStringExtra("donate_address");
                String getImage = getIntent().getStringExtra("t_image");



                t__name.setText(Dname);
                t__phone.setText(phoneN);
                tr_name.setText(tName);
                t__address.setText(addresss);
                Picasso.with(Needs_detailsActivity.this).load(getImage).placeholder(R.drawable.imgbg).into(tr_img);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}