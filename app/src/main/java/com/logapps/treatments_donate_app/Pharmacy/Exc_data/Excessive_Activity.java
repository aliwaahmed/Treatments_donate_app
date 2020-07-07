package com.logapps.treatments_donate_app.Pharmacy.Exc_data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.logapps.treatments_donate_app.Person.Accepted.Accepted_Class;
import com.logapps.treatments_donate_app.Person.Search.Search_class;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed_class;
import com.logapps.treatments_donate_app.Person.replace_data.Replace_adapter;
import com.logapps.treatments_donate_app.Person.replace_data.Replace_class;
import com.logapps.treatments_donate_app.Person.replace_data.Replacements_user;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.Pharmacy.P_data.P_class;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.history_data.History_class;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Excessive_Activity extends AppCompatActivity implements UserClick {

    TextView textView ;
    Context context ;
    private ProgressDialog mProgress;

    BottomNavigationView bottomNavigationView ;

    private DatabaseReference mPatientDatabase , database;
    private DatabaseReference databaseReference ;
    private FirebaseUser mCurrentUser , userId;
    private static final int GALLERY_PICK = 1;

    private RecyclerView mypostst;
    private String TAG = "hhhhhhhhhh";
    private ExcAdapter userAdapter ;
    ConstraintLayout constraintLayout ;

    private StorageReference mImageStorage;
    private ProgressDialog mProgressDilog;

    private static final int PICK_IMAGE = 1;
    Uri imageUri , profile_img;

    ImageView t_image ;

    DataSnapshot dataSnapshot ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excessive_);

        bottomNavigationView = findViewById(R.id.bottomNav);
        mypostst = findViewById(R.id.exc_recycler);

        constraintLayout = findViewById(R.id.constrain);


        mImageStorage = FirebaseStorage.getInstance().getReference();


        RecyclerView.LayoutManager recyce = new LinearLayoutManager(Excessive_Activity.this,LinearLayoutManager.VERTICAL,true);
        mypostst.setLayoutManager(recyce);

        bottomNavigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Excessive_Activity.this, "add post ...", Toast.LENGTH_SHORT).show();
            }
        });


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String current_uid = mCurrentUser.getUid();
        mPatientDatabase = FirebaseDatabase.getInstance().getReference().child("My_exc").child(current_uid);
        database =FirebaseDatabase.getInstance().getReference().child("ph_Users").child(current_uid);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("All_exc");

        fetchFeeds();

        bottomNavigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(Excessive_Activity.this);
                final View view = getLayoutInflater().inflate(R.layout.add_donate_dialog , null);




                final TextView your_name = view.findViewById(R.id.d_name);
                final TextInputEditText details = view.findViewById(R.id.details);
                final TextInputEditText address = view.findViewById(R.id.d_address);
                final TextInputEditText prize = view.findViewById(R.id.d_prize);
                final TextInputEditText phone = view.findViewById(R.id.d_call);
                final Button addTreat = view.findViewById(R.id.add_donate);
                final CircleImageView mDisplayImage = view.findViewById(R.id.image);
                t_image = view.findViewById(R.id.dia_t_image);

                //get image & name
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String name = dataSnapshot.child("ph_name").getValue().toString();
                        final String image = dataSnapshot.child("thumb_image").getValue().toString();


                        your_name.setText(name);



                        if (!image.equals("default")) {
                            Picasso.with(getApplicationContext()).load(image).networkPolicy(NetworkPolicy.OFFLINE)
                                    .placeholder(R.drawable.avatar).into(mDisplayImage, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {

                                    Picasso.with(getApplicationContext()).load(image).placeholder(R.drawable.avatar).into(mDisplayImage);
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                t_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pickImage();

                    }
                });



                alert.setView(view);
                final AlertDialog dialog = alert.create();
                dialog.show();


                addTreat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());

                        String donate_details = details.getText().toString();
                        String donate_address = address.getText().toString();
                        String donate_call = phone.getText().toString();
                        String donate_name = your_name.getText().toString();
                        String donate_prize = prize.getText().toString();
                        //image uri ...
                        final Uri resultUri = imageUri.normalizeScheme();

                        mProgress = new ProgressDialog(view.getContext());
                        mProgress.setTitle("Saving Changes");
                        mProgress.setMessage("Please wait while we save the changes");
                        mProgress.show();

                        Map map = new HashMap();
                        final String key = mPatientDatabase.push().getKey();
                        Map map1 = new HashMap();
                        map1.put(key, map);
                        Map mParent = new HashMap();
                        mPatientDatabase.push().setValue(mParent);


                        final StorageReference filepath = mImageStorage.child("exc_images").child(current_uid + System.currentTimeMillis() + ".jpg");

                        filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        final String downloadUrl = uri.toString();

                                        mPatientDatabase.child("my_exc"+key).child("exc_image").setValue(downloadUrl)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if (task.isSuccessful()) {
                                                        } else {
                                                            String message = task.getException().getMessage();
                                                        }
                                                    }

                                                });

                                        databaseReference.child("exc"+key).child("exc_image").setValue(downloadUrl)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if (task.isSuccessful()) {
                                                        } else {
                                                            String message = task.getException().getMessage();
                                                        }
                                                    }

                                                });

                                    }
                                });
                            }
                        });


                        //add name one
                        mPatientDatabase.child("my_exc"+key).child("name")
                                .setValue(donate_name)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });
                        //add name two
                        databaseReference.child("exc"+key).child("name")
                                .setValue(donate_name)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                        //____________________________________________
                        //____________________________________________

                        //add details one
                        mPatientDatabase.child("my_exc"+key).child("details")
                                .setValue(donate_details)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });


                        //add details two
                        databaseReference.child("exc"+key).child("details")
                                .setValue(donate_details)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                        //add time


                        mPatientDatabase.child("my_exc"+key).child("date")
                                .setValue(currentDateTimeString)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });


                        //add details two
                        databaseReference.child("exc"+key).child("date")
                                .setValue(currentDateTimeString)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                        //_________________

                        //add address one
                        mPatientDatabase.child("my_exc"+key).child("donate_address")
                                .setValue(donate_address)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                        //add address two
                        databaseReference.child("exc"+key).child("donate_address")
                                .setValue(donate_address)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });


                        //add phone number one
                        mPatientDatabase.child("my_exc"+key).child("donate_call").setValue(donate_call)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                        //prize

                        databaseReference.child("exc"+key).child("donate_prize")
                                .setValue(donate_prize)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });


                        //add phone number one
                        mPatientDatabase.child("my_exc"+key).child("donate_prize").setValue(donate_prize)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });





                        //_______________________________

                        //add phone number two
                        databaseReference.child("exc"+key).child("donate_call").setValue(donate_call)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){

                                            mProgress.dismiss();

                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                        dialog.dismiss();


                    }
                });
            }
        });

    }

    private void pickImage() {


        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, GALLERY_PICK);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getApplicationContext().getContentResolver(), imageUri);

                t_image.setImageBitmap(bitmap);

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
    }

    private void fetchFeeds() {

        String uid = mCurrentUser.getUid();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();



        database.child("My_exc").child(uid)

                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {

                            final ArrayList<ExcClass> feeds = new ArrayList<>();

                            for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                                ExcClass feed = new ExcClass(
                                        noteDataSnapshot.child("donate_prize").getValue(String.class)
                                        , noteDataSnapshot.child("donate_prize").getValue(String.class)
                                        , noteDataSnapshot.child("details").getValue(String.class)
                                        , noteDataSnapshot.child("date").getValue(String.class) ,
                                        noteDataSnapshot.child("details").getValue(String.class));

                                feeds.add(feed);

                            }
                            userAdapter = new ExcAdapter(Excessive_Activity.this);
                            userAdapter.setUsersData(feeds, Excessive_Activity.this);
                            mypostst.setAdapter(userAdapter);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}