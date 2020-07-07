package com.logapps.treatments_donate_app.donate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.logapps.treatments_donate_app.Person.Person_home_activity;
import com.logapps.treatments_donate_app.Person.Person_login_activity;
import com.logapps.treatments_donate_app.Person.Search.Search_class;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed_Adapter;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed_class;
import com.logapps.treatments_donate_app.Person.replace_data.Replace_class;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.Pharmacy.Exc_data.ExcClass;
import com.logapps.treatments_donate_app.Pharmacy.P_data.P_class;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.data.All_needs_Adapter;
import com.logapps.treatments_donate_app.donate.data.All_needs_class;
import com.logapps.treatments_donate_app.donate.history_data.DonateHistoryActivity;
import com.logapps.treatments_donate_app.donate.history_data.History_class;
import com.logapps.treatments_donate_app.donate.ph_donates.Ph_DonatesActivity;

import java.util.ArrayList;

public class Donate_home_activity extends AppCompatActivity implements UserClick {

    private Toolbar mToolbar ;
    private RecyclerView needs_list;
    private DatabaseReference mPatientDatabase , database;
    private DatabaseReference databaseReference ;
    private FirebaseUser mCurrentUser , userId;
    private StorageReference mImageStorage;
    private All_needs_Adapter userAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_home_activity);

        mToolbar = findViewById(R.id.toolbar);
        needs_list = findViewById(R.id._needs_list);


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


        database.child("needs")

                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {

                            final ArrayList<All_needs_class> feeds = new ArrayList<>();

                            for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                                All_needs_class feed = new All_needs_class(
                                        noteDataSnapshot.child("name").getValue(String.class)
                                        , noteDataSnapshot.child("details").getValue(String.class)
                                        , noteDataSnapshot.child("donate_call").getValue(String.class)
                                        , noteDataSnapshot.child("donate_address").getValue(String.class) ,
                                        noteDataSnapshot.child("t_image").getValue(String.class)
                                        ,noteDataSnapshot.child("phone").getValue(String.class)
                                        , noteDataSnapshot.child("address").getValue(String.class)
                                        , noteDataSnapshot.child("proof_image").getValue(String.class)
                                        ,noteDataSnapshot.child("id").getValue(String.class),
                                        noteDataSnapshot.child("id_image").getValue(String.class));

                                feeds.add(feed);

                            }
                            userAdapter = new All_needs_Adapter(Donate_home_activity.this);
                            userAdapter.setUsersData(feeds, Donate_home_activity.this);
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
        getMenuInflater().inflate(R.menu.charity_menu , menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_prifile){
            Intent i = new Intent(Donate_home_activity.this , DonateProfileActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);


        } else if (item.getItemId() == R.id.menu_logout) {

            Intent i = new Intent(Donate_home_activity.this , Donate_login_activity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }

        else if (item.getItemId() == R.id.menu_history) {

            Intent i = new Intent(Donate_home_activity.this , DonateHistoryActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }

        else if (item.getItemId() == R.id.menu_ph_corona) {

            Intent i = new Intent(Donate_home_activity.this , DonateCoronaActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }

        else if (item.getItemId() == R.id.menu_ph_donates) {

            Intent i = new Intent(Donate_home_activity.this , Ph_DonatesActivity.class);
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
}