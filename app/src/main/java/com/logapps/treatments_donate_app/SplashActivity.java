package com.logapps.treatments_donate_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(1500);
                    Intent i = new Intent(getApplicationContext() , MainActivity.class);
                    startActivity(i);
                    finish();

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}