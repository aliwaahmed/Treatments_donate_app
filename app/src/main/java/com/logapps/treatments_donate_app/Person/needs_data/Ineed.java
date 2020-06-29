package com.logapps.treatments_donate_app.Person.needs_data;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.logapps.treatments_donate_app.Person.Search.Search_class;
import com.logapps.treatments_donate_app.Person.replace_data.Replace_adapter;
import com.logapps.treatments_donate_app.Person.replace_data.Replace_class;
import com.logapps.treatments_donate_app.Person.replace_data.Replacements_user;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.Pharmacy.P_data.P_class;
import com.logapps.treatments_donate_app.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class Ineed extends Fragment implements UserClick {
    public Ineed() {
    }


    //---------------------
    private static final int GET_GALLERY_CODE2 = 1000;

    final private int GET_GALLERY_CODE = 111;
    final private int GET_GALLERY_CODE0 = 110;

    final private int GET_GALLERY_CODE1 = 1999;
    //---------------------

    TextView textView ;
    Context context ;
    private ProgressDialog mProgresse;

    BottomNavigationView bottomNavigationView ;

    private DatabaseReference mPatientDatabase , database;
    private DatabaseReference databaseReference ;
    private FirebaseUser mCurrentUser , userId;
    private static final int GALLERY_PICK = 1;

    private RecyclerView mypostst;
    private String TAG = "hhhhhhhhhh";
    private Ineed_Adapter userAdapter ;
    ConstraintLayout constraintLayout ;

    private StorageReference mImageStorage , NeedImage , id_image_storage;
    private ProgressDialog mProgressDilog;

    private static final int PICK_IMAGE = 1;
    Uri imageUri , NeedUri , idUri;

    ImageView t_image , proofImage , id_image;

    DataSnapshot dataSnapshot ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.user_needs_layout, container, false);



        bottomNavigationView = view.findViewById(R.id.bottomNav);
        mypostst = view.findViewById(R.id.needs_list);

        constraintLayout = view.findViewById(R.id.constrain);

        mImageStorage = FirebaseStorage.getInstance().getReference();
        NeedImage = FirebaseStorage.getInstance().getReference();
        id_image_storage = FirebaseStorage.getInstance().getReference();





        RecyclerView.LayoutManager recyce = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        mypostst.setLayoutManager(recyce);


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String current_uid = mCurrentUser.getUid();
        mPatientDatabase = FirebaseDatabase.getInstance().getReference().child("All needs").child(current_uid);
        database =FirebaseDatabase.getInstance().getReference().child("per_Users").child(current_uid);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("needs");

        fetchFeeds();

        bottomNavigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                final View view = getLayoutInflater().inflate(R.layout.needs_dialog , null);
                final TextView your_name = view.findViewById(R.id.d_name);
                final TextInputEditText details = view.findViewById(R.id.details);
                final TextInputEditText address = view.findViewById(R.id.d_address);
                final TextInputEditText phone = view.findViewById(R.id.d_call);
                final Button addNeed = view.findViewById(R.id.add_donate);
                final CircleImageView mDisplayImage = view.findViewById(R.id.image);

                t_image = view.findViewById(R.id.dia_t_image);
                proofImage = view.findViewById(R.id.proof_t_image);
                id_image = view.findViewById(R.id.id_t_image);



                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                        String name = dataSnapshot.child("per_name").getValue().toString();
                        final String image = dataSnapshot.child("thumb_image").getValue().toString();


                        your_name.setText(name);


                        if (!image.equals("default")) {
                            Picasso.with(getActivity()).load(image).networkPolicy(NetworkPolicy.OFFLINE)
                                    .placeholder(R.drawable.avatar).into(mDisplayImage, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {

                                    Picasso.with(getActivity()).load(image).placeholder(R.drawable.avatar).into(mDisplayImage);
                                }
                            });
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //choose image
                t_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, GET_GALLERY_CODE2);

                    }
                });
                proofImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, GET_GALLERY_CODE);
                    }
                });
                id_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, GET_GALLERY_CODE1);
                    }
                });

                alert.setView(view);
                final AlertDialog dialog = alert.create();
                dialog.show();

                addNeed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String donate_details = details.getText().toString();
                        String donate_address = address.getText().toString();
                        String donate_call = phone.getText().toString();
                        String donate_name = your_name.getText().toString();
                        //image uri ...
                        final Uri resultUri = imageUri.normalizeScheme();
                        final Uri need_uri = NeedUri.normalizeScheme();
                        final Uri id_uri = idUri.normalizeScheme();


                        mProgresse = new ProgressDialog(view.getContext());
                        mProgresse.setTitle("Saving Changes");
                        mProgresse.setMessage("Please wait while we save the changes");
                        mProgresse.show();

                        Map map = new HashMap();
                        final String key = mPatientDatabase.push().getKey();
                        Map map1 = new HashMap();
                        map1.put(key, map);
                        Map mParent = new HashMap();
                        mPatientDatabase.push().setValue(mParent);


                        final StorageReference filepath = mImageStorage.child("proof_images_need").child(current_uid + System.currentTimeMillis() + ".jpg");
                        filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        final String downloadUrl = uri.toString();
                                        mPatientDatabase.child("my_need"+key).child("t_image").setValue(downloadUrl)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if (task.isSuccessful()) {
                                                        } else {
                                                            String message = task.getException().getMessage();
                                                        }
                                                    }

                                                });

                                        final String downloadUrl2 = uri.toString();
                                        databaseReference.child("need"+key).child("t_image").setValue(downloadUrl2)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if (task.isSuccessful()) {
                                                        } else {
                                                            String message = task.getException().getMessage();
                                                        }
                                                    }

                                                });


                                        final StorageReference filepath2 = NeedImage.child("d_images_need").child(current_uid + System.currentTimeMillis() + ".jpg");

                                        filepath2.putFile(need_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                filepath2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {

                                                        final String downloadUrl = uri.toString();
                                                        databaseReference.child("need"+key).child("proof_image").setValue(downloadUrl)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {

                                                                        if (task.isSuccessful()) {
                                                                        } else {
                                                                            String message = task.getException().getMessage();
                                                                        }
                                                                    }

                                                                });

                                                        mPatientDatabase.child("my_need"+key).child("proof_image").setValue(downloadUrl)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {

                                                                        if (task.isSuccessful()) {



                                                                            final StorageReference filepath3 = id_image_storage.child("id_images_need").child(current_uid + System.currentTimeMillis()  + ".jpg");
                                                                            filepath3.putFile(id_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                                                @Override
                                                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                                    filepath3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                        @Override
                                                                                        public void onSuccess(Uri uri) {

                                                                                            final String downloadUrl = uri.toString();
                                                                                            databaseReference.child("need"+key).child("id_image").setValue(downloadUrl)
                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                        @Override
                                                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                                                            if (task.isSuccessful()) {
                                                                                                            } else {
                                                                                                                String message = task.getException().getMessage();
                                                                                                            }
                                                                                                        }

                                                                                                    });

                                                                                            mPatientDatabase.child("my_need"+key).child("id_image").setValue(downloadUrl)
                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                        @Override
                                                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                                                            if (task.isSuccessful()) {
                                                                                                                if(mProgresse.isShowing())
                                                                                                                {
                                                                                                                    mProgresse.dismiss();
                                                                                                                }
                                                                                                            } else {
                                                                                                                String message = task.getException().getMessage();
                                                                                                            }
                                                                                                        }

                                                                                                    });
                                                                                        }
                                                                                    });
                                                                                }
                                                                            });
















                                                                        } else {
                                                                            String message = task.getException().getMessage();
                                                                        }
                                                                    }








                                                                });
                                                    }
                                                });
                                            }
                                        });














                                    }
                                });
                            }
                        });



//                        ________________________________________________________________________



//                        _________________________________________________________________________ id_Image




//                        ___________________________________________________________________________




                        //add name one
                        mPatientDatabase.child("my_need"+key).child("name")
                                .setValue(donate_name)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){


                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });
                        //add name two
                        databaseReference.child("need"+key).child("name")
                                .setValue(donate_name)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){


                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                        //add details one
                        mPatientDatabase.child("my_need"+key).child("details")
                                .setValue(donate_details)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){


                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });


                        //add details two
                        databaseReference.child("need"+key).child("details")
                                .setValue(donate_details)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){


                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                        //add address one
                        mPatientDatabase.child("my_need"+key).child("donate_address")
                                .setValue(donate_address)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){


                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                        //add address two
                        databaseReference.child("need"+key).child("donate_address")
                                .setValue(donate_address)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){


                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });


                        //add phone number one
                        mPatientDatabase.child("my_need"+key).child("donate_call").setValue(donate_call)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){


                                        } else {

                                            Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });


                        //add phone number two
                        databaseReference.child("need"+key).child("donate_call").setValue(donate_call)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){


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

        return view ;
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


        switch (requestCode) {
            case GET_GALLERY_CODE2:

                if (resultCode == RESULT_OK) {
                    Glide.with(getActivity()).load(data.getData()).into(t_image);

                    imageUri = data.getData();

                } else if (requestCode == RESULT_CANCELED) {
                    Log.e("cancel", "cancel");

                }
                break;

            case GET_GALLERY_CODE:

                if (resultCode == RESULT_OK) {
                    Glide.with(getActivity()).load(data.getData()).into(proofImage);

                    NeedUri = data.getData();

                } else if (requestCode == RESULT_CANCELED) {
                    Log.e("cancel", "cancel");

                }
                break;

            case GET_GALLERY_CODE1 :
                if (resultCode == RESULT_OK) {
                    Glide.with(getActivity()).load(data.getData()).into(id_image);

                    idUri = data.getData();

                } else if (requestCode == RESULT_CANCELED) {
                    Log.e("cancel", "cancel");

                }
                break;


            default: // none of these
                break;
        }





    }


    public void fetchFeeds() {

        String uid = mCurrentUser.getUid();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();



        database.child("All needs").child(uid)

                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {

                            final ArrayList<Ineed_class> feeds = new ArrayList<>();

                            for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                                Ineed_class feed = new Ineed_class(
                                        noteDataSnapshot.child("name").getValue(String.class)
                                        , noteDataSnapshot.child("details").getValue(String.class)
                                        , noteDataSnapshot.child("donate_call").getValue(String.class)
                                        , noteDataSnapshot.child("donate_address").getValue(String.class) ,
                                        noteDataSnapshot.child("t_image").getValue(String.class)
                                ,noteDataSnapshot.child("prof_image").getValue(String.class)
                                , noteDataSnapshot.child("address").getValue(String.class)
                                , noteDataSnapshot.child("phone").getValue(String.class)
                                ,noteDataSnapshot.child("id").getValue(String.class));

                                feeds.add(feed);

                            }
                            userAdapter = new Ineed_Adapter(Ineed.this);
                            userAdapter.setUsersData(feeds, Ineed.this);
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
}
