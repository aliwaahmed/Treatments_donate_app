package com.logapps.treatments_donate_app.donate.data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.logapps.treatments_donate_app.Person.needs_data.Needs_detailsActivity;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.Donate_home_activity;
import com.logapps.treatments_donate_app.donate.history_data.DonateHistoryActivity;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Donate_DetailsActivity extends AppCompatActivity {

    TextView t__name , t__phone , t__address , tr_name , id_txt , ignoreTxt , acceptedTxt , chName;
    Button accept , ignore ;
    DatabaseReference mUserDatabase , mHistoryDatabase , nameDatabase , needDatabase;
    FirebaseUser uid ;
    ImageView tr_img,_treat_img,_id_img  , back;
    private FirebaseUser mCurrent_Users , user_id  ;
    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate__details);

        id_txt = findViewById(R.id.t_id);
        chName = findViewById(R.id.ch_name);
        t__name = findViewById(R.id.t_name);
        back = findViewById(R.id.back_btn);
        t__address = findViewById(R.id.t_address);
        t__phone = findViewById(R.id.t_phone);
        tr_name = findViewById(R.id.tr_name);
        ignoreTxt = findViewById(R.id.ignore_txt);
        acceptedTxt = findViewById(R.id.accepted_txt);
        tr_img = findViewById(R.id._proof_img);
        _treat_img=findViewById(R.id._treat_img);
        _id_img=findViewById(R.id._id_img);
        accept = findViewById(R.id.accept_btn);
        ignore = findViewById(R.id.ignore_btn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Donate_DetailsActivity.this , Donate_home_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        id_txt.setVisibility(View.GONE);
       // chName.setVisibility(View.GONE);

        needDatabase = FirebaseDatabase.getInstance().getReference().child("needs");
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Accepted");
        mHistoryDatabase =  FirebaseDatabase.getInstance().getReference().child("History");
        mCurrent_Users = FirebaseAuth.getInstance().getCurrentUser();
        nameDatabase = FirebaseDatabase.getInstance().getReference().child("do_Users").child(mCurrent_Users.getUid());




        user_id = FirebaseAuth.getInstance().getCurrentUser();


        try {


            String Dname  = getIntent().getStringExtra("name");
            String tName = getIntent().getStringExtra("details");
            String phoneN = getIntent().getStringExtra("donate_call");
            String addresss = getIntent().getStringExtra("donate_address");
            String userId = getIntent().getStringExtra("id");
            String getImage = getIntent().getStringExtra("t_image");
            String getImage1 = getIntent().getStringExtra("id_image");
            String getImage2 = getIntent().getStringExtra("proof_image");

            nameDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String cha_name = dataSnapshot.child("do_name").getValue().toString();
                    chName.setText(cha_name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



            Picasso.with(Donate_DetailsActivity.this).load(getImage1).placeholder(R.drawable.imgbg).into(_id_img);
            Picasso.with(Donate_DetailsActivity.this).load(getImage2).placeholder(R.drawable.imgbg).into(_treat_img);
            Picasso.with(Donate_DetailsActivity.this).load(getImage).placeholder(R.drawable.imgbg).into(tr_img);

            t__name.setText(Dname);
            t__phone.setText(phoneN);
            tr_name.setText(tName);
            t__address.setText(addresss);
            id_txt.setText(userId);



        }
        catch (Exception e)
        {

        }


        final String donate_name = t__name.getText().toString();
        final String idParent = id_txt.getText().toString();
        final String donate_call = t__phone.getText().toString();
        final String donate_address = t__address.getText().toString();
        final String charity_name = chName.getText().toString();
        final String treatment_name = tr_name.getText().toString();



        final String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accept.setText("Accepted");
                ignore.setVisibility(View.GONE);
                accept.setVisibility(View.GONE);

                acceptedTxt.setVisibility(View.VISIBLE);

                Toast.makeText(Donate_DetailsActivity.this, "" + currentDateTimeString, Toast.LENGTH_LONG).show();

                Intent i = new Intent(Donate_DetailsActivity.this , DonateHistoryActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                Map map = new HashMap();
                final String key = mUserDatabase.push().getKey();
                Map map1 = new HashMap();
                map1.put(key, map);
                Map mParent = new HashMap();
                mUserDatabase.push().setValue(mParent);


                //history
                mHistoryDatabase.child(mCurrent_Users.getUid()).child("accept" + key)
                        .child("name").setValue(donate_name)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                mHistoryDatabase.child(mCurrent_Users.getUid()).child("accept" + key)
                        .child("phone").setValue(donate_call)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                mHistoryDatabase.child(mCurrent_Users.getUid()).child("accept" + key)
                        .child("address").setValue(donate_address)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });

                mHistoryDatabase.child(mCurrent_Users.getUid()).child("accept" + key)
                        .child("time").setValue(currentDateTimeString)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });

                //_________________________________________


                mUserDatabase.child(idParent).child("accept" + key)
                        .child("name").setValue(donate_name)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                mUserDatabase.child(idParent).child("accept" + key)
                        .child("phone").setValue(donate_call)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                mUserDatabase.child(idParent).child("accept" + key)
                        .child("address").setValue(donate_address)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                mUserDatabase.child(idParent).child("accept" + key)
                        .child("treatment_name").setValue(treatment_name)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });

                mUserDatabase.child(idParent).child("accept" + key)
                        .child("time").setValue(currentDateTimeString)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });

                //delete need after accept it
//                needDatabase.child("needs").removeValue();



                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.child("needs").child("need");

                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });






            }
        });

        ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }
}