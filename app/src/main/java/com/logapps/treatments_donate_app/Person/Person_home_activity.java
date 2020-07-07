package com.logapps.treatments_donate_app.Person;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.Person.Search.Search_activity;

public class Person_home_activity extends AppCompatActivity {


    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    private ViewPager mViewPager;
    private SerctionPagerAdapter mSerctionPagerAdapter;
    private TextView drName ;
    private TabLayout mTabLayout;
    private CircleImageView mDisplayImage ;

    private DatabaseReference mUserDatabase , mDatabase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_home_activity);

        mViewPager = findViewById(R.id.pager);
        mTabLayout = findViewById(R.id.tablayout);
        mToolbar = findViewById(R.id.toolbar);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("per_Users").child(current_uid);

        mAuth = FirebaseAuth.getInstance();
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);



        mSerctionPagerAdapter = new SerctionPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSerctionPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu , menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_prifile){
            Intent i = new Intent(Person_home_activity.this , Person_profile_activity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }else if (item.getItemId() == R.id.menu_privacy){
            Toast.makeText(this, "privacy", Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.menu_logout) {
            Intent i = new Intent(Person_home_activity.this , Person_login_activity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }

        else if (item.getItemId() == R.id.menu_corona) {
            Intent i = new Intent(Person_home_activity.this , PersonCoronaActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }

        else if (item.getItemId() == R.id.menu_search) {
            Intent i = new Intent(Person_home_activity.this , Search_activity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }

        return true ;
    }
}
