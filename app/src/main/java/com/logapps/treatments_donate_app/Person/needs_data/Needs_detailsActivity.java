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
    ImageView tr_img,_treat_img,_id_img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needs_details);

        t__name = findViewById(R.id.t_name);
        t__address = findViewById(R.id.t_address);
        t__phone = findViewById(R.id.t_phone);
        tr_name = findViewById(R.id.tr_name);
        tr_img = findViewById(R.id._proof_img);
        _treat_img=findViewById(R.id._treat_img);
        _id_img=findViewById(R.id._id_img);


        try {


            String Dname  = getIntent().getStringExtra("name");
            String tName = getIntent().getStringExtra("details");
            String phoneN = getIntent().getStringExtra("donate_call");
            String addresss = getIntent().getStringExtra("donate_address");
            String getImage = getIntent().getStringExtra("t_image");

            String getImage1 = getIntent().getStringExtra("id_image");
            String getImage2 = getIntent().getStringExtra("proof_image");

            Picasso.with(Needs_detailsActivity.this).load(getImage1).placeholder(R.drawable.imgbg).into(_id_img);
            Picasso.with(Needs_detailsActivity.this).load(getImage2).placeholder(R.drawable.imgbg).into(_treat_img);

            t__name.setText(Dname);
            t__phone.setText(phoneN);
            tr_name.setText(tName);
            t__address.setText(addresss);
            Picasso.with(Needs_detailsActivity.this).load(getImage).placeholder(R.drawable.imgbg).into(tr_img);
        }
        catch (Exception e)
        {

        }





    }
}