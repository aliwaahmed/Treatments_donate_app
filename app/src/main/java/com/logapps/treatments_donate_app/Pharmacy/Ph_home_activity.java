package com.logapps.treatments_donate_app.Pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
import com.logapps.treatments_donate_app.Pharmacy.Exc_data.Excessive_Activity;
import com.logapps.treatments_donate_app.Pharmacy.P_data.P_Adapter;
import com.logapps.treatments_donate_app.Pharmacy.P_data.P_class;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.history_data.History_class;

import java.util.ArrayList;

public class Ph_home_activity extends AppCompatActivity implements UserClick {

    //variables
    private Toolbar mToolbar ;
    private RecyclerView needs_list;
    private DatabaseReference mPatientDatabase , database;
    private DatabaseReference databaseReference ;
    private FirebaseUser mCurrentUser , userId;
    private StorageReference mImageStorage;
    private P_Adapter userAdapter ;

    Button logout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph_home);

        logout = findViewById(R.id._logout);

        //just for test .. don't delete !!!!

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ph_home_activity.this , Ph_login_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });


        mToolbar = findViewById(R.id.toolbar);
        needs_list = findViewById(R.id._needs_list);


        //make sure that the list is vertical
        RecyclerView.LayoutManager recyce = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true);
        needs_list.setLayoutManager(recyce);


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

        database.child("Replacements")

                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {

                            final ArrayList<P_class> feeds = new ArrayList<>();

                            for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                                P_class feed = new P_class(
                                        noteDataSnapshot.child("name").getValue(String.class)
                                        , noteDataSnapshot.child("details").getValue(String.class)
                                        , noteDataSnapshot.child("t_image").getValue(String.class)
                                        , noteDataSnapshot.child("ex_date").getValue(String.class) ,
                                        noteDataSnapshot.child("ex_image").getValue(String.class)
                                        );

                                feeds.add(feed);

                            }
                            userAdapter = new P_Adapter(Ph_home_activity.this);
                            userAdapter.setUsersData(feeds, Ph_home_activity.this);
                            needs_list.setAdapter(userAdapter);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.pharmacy_menu , menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_prifile){
            Intent i = new Intent(Ph_home_activity.this , Ph_profile_activity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (item.getItemId() == R.id.menu_logout) {

            Intent i = new Intent(Ph_home_activity.this , Ph_login_activity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }
        else if (item.getItemId() == R.id._excessive) {

            Intent i = new Intent(Ph_home_activity.this , Excessive_Activity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }
        else if (item.getItemId() == R.id._corona) {

            Intent i = new Intent(Ph_home_activity.this , Ph_CoronaActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }

        return true ;
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
