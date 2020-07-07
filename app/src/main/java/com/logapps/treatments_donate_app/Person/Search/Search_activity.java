package com.logapps.treatments_donate_app.Person.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.logapps.treatments_donate_app.Person.Accepted.Accepted_Class;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed_class;
import com.logapps.treatments_donate_app.Person.replace_data.Replace_class;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.Pharmacy.Exc_data.ExcClass;
import com.logapps.treatments_donate_app.Pharmacy.P_data.P_class;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.history_data.History_class;

import java.util.ArrayList;


public class Search_activity extends AppCompatActivity implements UserClick {


    private DatabaseReference mPatientDatabase;
    private FirebaseUser mCurrentUser , userId;
    private RecyclerView mPatientsList;
    private Search_Adapter userAdapter ;

    private SearchView _search_txt;
    LinearLayout linearLayout ;

    private    Search_class feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);

        _search_txt=findViewById(R.id._search_txt);

        mPatientsList = findViewById(R.id.search_list);
        linearLayout = findViewById(R.id.linear);



        RecyclerView.LayoutManager recyce = new LinearLayoutManager(Search_activity.this , LinearLayoutManager.VERTICAL ,true);
        mPatientsList.setLayoutManager(recyce);


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();
        mPatientDatabase = FirebaseDatabase.getInstance().getReference().child("Replacements");

        //https://www.tutorialspoint.com/how-to-use-searchview-in-android
        //https://androidmonks.com/searchview/





        String uid = mCurrentUser.getUid();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.child("Replacements")

                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {


                            final ArrayList<Search_class> feeds = new ArrayList<>();

                            for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                              feed = new Search_class(
                                        noteDataSnapshot.child("name").getValue(String.class)
                                        , noteDataSnapshot.child("t_image").getValue(String.class)
                                        ,noteDataSnapshot.child("details").getValue(String.class)
                                        ,noteDataSnapshot.child("donate_prize").getValue(String.class)
                                        ,noteDataSnapshot.child("em").getValue(String.class)
                                        ,noteDataSnapshot.child("t_image").getValue(String.class));

                                //  DatesClass feed = noteDataSnapshot.getValue(DatesClass.class);
                                feed.setId(noteDataSnapshot.getKey());
                                feeds.add(feed);
                            }
                            userAdapter = new Search_Adapter(Search_activity.this);
                            userAdapter.setUsersData(feeds, Search_activity.this);
                            mPatientsList.setAdapter(userAdapter);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });





        _search_txt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(feed.getEm().contains(query.toString())){
                    userAdapter.getFilter().filter(query);
                }else{
                    Toast.makeText(getApplicationContext(), "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                    userAdapter.getFilter().filter(newText.toString());

                return false;
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