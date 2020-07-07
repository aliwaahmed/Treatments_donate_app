package com.logapps.treatments_donate_app.donate.history_data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.logapps.treatments_donate_app.Person.Accepted.Accepted_Class;
import com.logapps.treatments_donate_app.Person.Search.Search_class;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed_class;
import com.logapps.treatments_donate_app.Person.replace_data.Replace_class;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.Pharmacy.Exc_data.ExcClass;
import com.logapps.treatments_donate_app.Pharmacy.P_data.P_class;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.Donate_home_activity;
import com.logapps.treatments_donate_app.donate.data.All_needs_Adapter;
import com.logapps.treatments_donate_app.donate.data.All_needs_class;
import com.logapps.treatments_donate_app.donate.data.Donate_DetailsActivity;

import java.util.ArrayList;

public class DonateHistoryActivity extends AppCompatActivity implements UserClick {

    private Toolbar mToolbar ;
    private RecyclerView history_list;
    private ImageView backbtn ;
    private DatabaseReference mPatientDatabase , database;
    private DatabaseReference databaseReference ;
    private FirebaseUser mCurrentUser , userId;
    private StorageReference mImageStorage;
    private HistoryAdapter userAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_history);

        mToolbar = findViewById(R.id.toolbar);
        backbtn = findViewById(R.id.back_btn);
        history_list = findViewById(R.id.history_recycler);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DonateHistoryActivity.this , Donate_home_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        RecyclerView.LayoutManager recyce = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true);
        history_list.setLayoutManager(recyce);


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String current_uid = mCurrentUser.getUid();

        mPatientDatabase = FirebaseDatabase.getInstance().getReference().child("All Replace").child(current_uid);
        database =FirebaseDatabase.getInstance().getReference().child("per_Users").child(current_uid);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Replacements");

        fetchFeeds();


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void fetchFeeds() {

        String uid = mCurrentUser.getUid();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.child("History").child(uid)

                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {

                            final ArrayList<History_class> feeds = new ArrayList<>();

                            for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                                History_class feed = new History_class(
                                        noteDataSnapshot.child("name").getValue(String.class)
                                        , noteDataSnapshot.child("time").getValue(String.class));

                                feeds.add(feed);

                            }
                            userAdapter = new HistoryAdapter(DonateHistoryActivity.this);
                            userAdapter.setUsersData(feeds, DonateHistoryActivity.this);
                            history_list.setAdapter(userAdapter);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    @Override
    public void asd(Replace_class replace_class) {

    }

    @Override
    public void asd(Ineed_class ineed_class) {

    }

    @Override
    public void asd(Search_class search_class) {

    }

    @Override
    public void asd(P_class p_class) {

    }

    @Override
    public void asd(History_class historyClass) {

    }

    @Override
    public void asd(Accepted_Class accepted_class) {

    }

    @Override
    public void asd(ExcClass excClass) {

    }
}