package com.example.loginface.loginface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ricardo on 08/03/2015.
 */
public class Waiting extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting);


        Button recivedButton = (Button) findViewById(R.id.buttonRecived);
        recivedButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent succes = new Intent(Waiting.this, Succes.class);
                startActivity(succes);
            }
        });


    }
}