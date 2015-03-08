package com.example.loginface.loginface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ricardo on 08/03/2015.
 */
public class Confirmation extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);


        Button acceptButton = (Button) findViewById(R.id.buttonAccept);
        acceptButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent wait = new Intent(Confirmation.this, Waiting.class);
                startActivity(wait);
            }
        });


    }
}
