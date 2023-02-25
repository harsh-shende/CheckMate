package com.harshshende.checkmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Handler to display splash screen animation for 5600 ms
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i01=new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(i01);
            }
        },2800);
    }
}