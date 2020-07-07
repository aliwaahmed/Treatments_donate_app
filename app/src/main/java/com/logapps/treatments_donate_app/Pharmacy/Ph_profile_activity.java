package com.logapps.treatments_donate_app.Pharmacy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import com.logapps.treatments_donate_app.Person.Person_profile_activity;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.DonateProfileActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class Ph_profile_activity extends AppCompatActivity {

    //variables
    private static final int GALLERY_PICK = 1;
    private DatabaseReference mUserDatabase , mDatabase;
    private FirebaseUser mCurrentUser;
    private CircleImageView mDisplayImage;
    private TextView mName, phoneNumber, manager , address;
    private Button editBtn;
    private ImageView change_image;
    private StorageReference mImageStorage;
    private ProgressDialog mProgressDilog;
    private DatabaseReference mPatientDatabase ;
    private ProgressDialog mProgress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph_profile_activity);

        //initialize
        mDisplayImage = findViewById(R.id.profile_pic);
        mName = findViewById(R.id.info_name);
        phoneNumber = findViewById(R.id.info_phone);
        manager = findViewById(R.id.info_manage);
        address = findViewById(R.id.info_address);
        change_image = findViewById(R.id.changeImage);
        editBtn = findViewById(R.id.edit_btn);

        // enable firebase libraries
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_id = mCurrentUser.getUid();
        mPatientDatabase = FirebaseDatabase.getInstance().getReference().child("ph_Users").child(current_id);

        //click on bottom navigation to show the dialog
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intialize values to upload it to firebase
                final AlertDialog.Builder alert = new AlertDialog.Builder(Ph_profile_activity.this);
                View view = getLayoutInflater().inflate(R.layout.edit_profile_charity , null);
                final EditText Dname = view.findViewById(R.id.dia_name);
                final EditText Dphone = view.findViewById(R.id.dia_phone);
                final EditText Dmanager = view.findViewById(R.id.dia_manager_name);
                final EditText Daddress = view.findViewById(R.id.dia_address);
                final Button Dsave = view.findViewById(R.id.dia_btn);
                alert.setView(view);
                final AlertDialog dialog = alert.create();
                dialog.show();

                //data
                mUserDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String info_name = dataSnapshot.child("ph_name").getValue().toString();
                        String info_phone = dataSnapshot.child("ph_phone").getValue().toString();
                        String info_add = dataSnapshot.child("ph_address").getValue().toString();
                       // String info_manager_name = dataSnapshot.child("do_man_name").getValue().toString();

                        Dname.setText(info_name);
                        Dphone.setText(info_phone);
                        Daddress.setText(info_add);
                        //Dmanager.setText(info_manager_name);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //press on save button
                Dsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // progress dialoge ... loading view
                        mProgress = new ProgressDialog(Ph_profile_activity.this);
                        mProgress.setTitle("Saving Changes");
                        mProgress.setMessage("Please wait while we save the changes");
                        mProgress.show();

                        //getting data from edittext
                        String DoctorName = Dname.getText().toString();
                        String DoctorAddress = Daddress.getText().toString();
                        String DoctorPhone = Dphone.getText().toString();
                        //String Managername = Dmanager.getText().toString();




                        //upload pharmacy name to specific root in database
                        mUserDatabase.child("ph_name").setValue(DoctorName).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){

                                    mProgress.dismiss();


                                } else {

                                    Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                                }

                            }
                        });

                        //upload phone number
                        mUserDatabase.child("ph_phone").setValue(DoctorPhone).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){

                                    mProgress.dismiss();

                                } else {

                                    Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                                }

                            }
                        });

                        //upload address
                        mUserDatabase.child("ph_address").setValue(DoctorAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){

                                    mProgress.dismiss();

                                } else {

                                    Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                                }

                            }
                        });
                        dialog.dismiss();
                    }
                });
            }
        });

        //firebase instance
        mImageStorage = FirebaseStorage.getInstance().getReference();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("ph_Users");

        //choose image
        change_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_PICK);

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(Ph_profile_activity.this);
            }
        });

        //firebase init again
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("ph_Users").child(current_uid);

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //get the data from database
                String name = dataSnapshot.child("ph_name").getValue().toString();
                String phone = dataSnapshot.child("ph_phone").getValue().toString();
                String Iaddress = dataSnapshot.child("ph_address").getValue().toString();
               // String Man_name = dataSnapshot.child("do_man_name").getValue().toString();
                final String image = dataSnapshot.child("image").getValue().toString();
                String thumb_image = dataSnapshot.child("thumb_image").getValue().toString();

                mName.setText(name);
                phoneNumber.setText(phone);
                address.setText(Iaddress);
           //     manager.setText(Man_name);

                //check if the value of image = "" , then upload the image url
                if (!image.equals("")) {
                    Picasso.with(Ph_profile_activity.this).load(image).networkPolicy(NetworkPolicy.OFFLINE)
                            .placeholder(R.drawable.avatar).into(mDisplayImage, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                            Picasso.with(Ph_profile_activity.this).load(image).placeholder(R.drawable.avatar).into(mDisplayImage);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //this method used to choose image from gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setAspectRatio(1, 1)
                    .start(Ph_profile_activity.this);
        }
        //profile image
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                mProgressDilog = new ProgressDialog(Ph_profile_activity.this);
                mProgressDilog.setTitle("Uploading Image...");
                mProgressDilog.setMessage("Please wait until uploading your image");
                mProgressDilog.setCanceledOnTouchOutside(false);
                mProgressDilog.show();

                final Uri resultUri = result.getUri();

                //   File thumb_filePath=new File(resultUri.getPath());

                String current_user_id = mCurrentUser.getUid();
                Uri imageUri = data.getData();

                //crop image
                CropImage.activity(imageUri)
                        .setAspectRatio(1, 1)
                        .setMinCropWindowSize(500, 500)
                        .start(this);
                // uploading photo to page all users
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //  imageUri.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                final byte[] thumb_byte = baos.toByteArray();

                //upload image to storage with ex"jpg"
                final StorageReference filepath = mImageStorage.child("profile_images").child(current_user_id + ".jpg");
                final StorageReference thumb_filepath = mImageStorage.child("profile_images").child("thumb").child(current_user_id + ".jpg");

                //getting the image URL
                filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                final String downloadUrl = uri.toString();
                                //upload the image to firebase database with url
                                mUserDatabase.child("image").setValue(downloadUrl)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                mProgressDilog.dismiss();
                                                if (task.isSuccessful()) {

                                                    Toast.makeText(Ph_profile_activity.this, "Profile image stored to firebase database successfully.", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    String message = task.getException().getMessage();
                                                    Toast.makeText(Ph_profile_activity.this, "Error Occured..." + message, Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                        });

                                //upload the image to firebase database with url
                                mUserDatabase.child("thumb_image").setValue(downloadUrl)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                mProgressDilog.dismiss();
                                                if (task.isSuccessful()) {
                                                    Intent selfIntent = new Intent(Ph_profile_activity.this, Person_profile_activity.class);
                                                    startActivity(selfIntent);
                                                    Toast.makeText(Ph_profile_activity.this, "Profile image stored to firebase database successfully.", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    String message = task.getException().getMessage();
                                                    Toast.makeText(Ph_profile_activity.this, "Error Occured..." + message, Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        });
                    }
                });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }
    //----------------------
}