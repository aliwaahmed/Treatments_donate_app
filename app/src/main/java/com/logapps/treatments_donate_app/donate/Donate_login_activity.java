package com.logapps.treatments_donate_app.donate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.logapps.treatments_donate_app.MainActivity;
import com.logapps.treatments_donate_app.Person.Person_home_activity;
import com.logapps.treatments_donate_app.Person.Person_login_activity;
import com.logapps.treatments_donate_app.Person.Person_register_activity;
import com.logapps.treatments_donate_app.R;

public class Donate_login_activity extends AppCompatActivity {

    //vars
    TextView newaccount ;
    ImageView back ;

    TextInputEditText email , password ;
    Button login ;
    private ProgressDialog LoginProgress;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_login_activity);

        newaccount = findViewById(R.id.new_account);
        back = findViewById(R.id.back_btn);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_btn);
        newaccount = findViewById(R.id.new_account);


        //firebase auth
        mAuth = FirebaseAuth.getInstance();
        //progress dialog
        LoginProgress = new ProgressDialog(this);

        //click on back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Donate_login_activity.this , MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        newaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Donate_login_activity.this , Donate_register_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });


        //login button click
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Pass = password.getText().toString().trim();

                if (!TextUtils.isEmpty(Email) || !TextUtils.isEmpty(Pass)){
                    LoginProgress.setTitle("Logging in");
                    LoginProgress.setMessage("Please wait while checking your information");
                    LoginProgress.setCanceledOnTouchOutside(false);
                    LoginProgress.show();
                    loginUser(Email , Pass);
                }
            }
        });

    }

    private void loginUser(String email, String pass) {

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Donate_login_activity.this, "success",
                                    Toast.LENGTH_SHORT).show();
                            LoginProgress.dismiss();
                            Intent i = new Intent(Donate_login_activity.this, Donate_home_activity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            finish();
                            FirebaseUser user = mAuth.getCurrentUser();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            // Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                            Toast.makeText(Donate_login_activity.this, "Please make sure your information is true.",
                                    Toast.LENGTH_LONG).show();
                            LoginProgress.hide();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
