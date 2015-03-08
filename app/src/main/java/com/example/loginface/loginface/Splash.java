package com.example.loginface.loginface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Ricardo on 08/03/2015.
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slash_screen);
        int myTimer = 5000;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

                Intent i = new Intent(Splash.this, Login.class);
                startActivity(i);
                finish();
            }
        }, myTimer);
    }}