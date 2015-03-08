package com.example.loginface.loginface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ricardo on 08/03/2015.
 */
public class Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup);

        Button entrabtn = (Button) findViewById(R.id.entrabutton);
        entrabtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewCatalog = new Intent(getBaseContext(), CatalogActivity.class);
                startActivity(viewCatalog);
            }
        });

        Button registrabtn = (Button) findViewById(R.id.registrabutton);
        registrabtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewSignup = new Intent(getBaseContext(), MainActivity.class);
                startActivity(viewSignup);
            }
        });


    }
}
