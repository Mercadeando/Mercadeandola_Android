package com.example.loginface.loginface;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.LoginButton;
import com.facebook.widget.WebDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/*
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                startActivity(intent);
            }
        });
    }

*/
public class MainActivity extends ActionBarActivity implements View.OnClickListener, LoginButton.OnErrorListener, Session.StatusCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pa sacar el hasis, forma facil de generar la key hash que vamos a colocr el developer.facebook
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.loginface.loginface",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                //segunda opcion para que ejecute
                md.update(signature.toByteArray());
                Log.d("TAG_KEY_HASH", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        LoginButton loginButtonFacebook = (LoginButton) findViewById(R.id.buttonLoginFacebook);
        loginButtonFacebook.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday"));
        //implementar interfaz View.
        //
        loginButtonFacebook.setOnErrorListener(this);
        //si manda un error
        loginButtonFacebook.setSessionStatusCallback(this);


      /*  findViewById(R.id.buttonLoginFacebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

       */

        //findViewById(R.id.buttonPefil).setOnClickListener(this);
        //findViewById(R.id.buttonRequestJson).setOnClickListener(this);
        //findViewById(R.id.buttonShare).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Session session = Session.getActiveSession();

        if (session != null && session.isOpened()) {

            switch (v.getId()) {
                case R.id.buttonPefil:
                    Intent intentPerfil = new Intent(MainActivity.this, PefilActivity.class);
                    startActivity(intentPerfil);

                    break;
                case R.id.buttonRequestJson:
                    Intent intentJson = new Intent(MainActivity.this, JsonActivity.class);
                    startActivity(intentJson);
                    break;
                case R.id.buttonShare:
                    sharedByFacebook();
                    break;
            }

        } else {
            Toast.makeText(MainActivity.this, R.string.message_not_logged, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onError(FacebookException e) {

    }

    @Override
    public void call(Session session, SessionState sessionState, Exception e) {

        if (sessionState.isOpened()) {
            Request.newMeRequest(session,
                    new Request.GraphUserCallback() {
                        @Override
                        public void onCompleted(GraphUser user,
                                                Response response) {
                            if (user != null) {

                                Toast.makeText(MainActivity.this, getString(R.string.welcome) + " " + user.getName(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                                startActivity(intent);


                            } else {
                                Toast.makeText(MainActivity.this, R.string.message_error, Toast.LENGTH_SHORT).show();
                                logoutFacebook();
                            }
                        }
                    }).executeAsync();

           // Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            //startActivity(intent);
        } else if (sessionState.isClosed()) {
            Toast.makeText(MainActivity.this, R.string.message_log_out, Toast.LENGTH_SHORT).show();
        }

        /*
        if (sessionState.isOpened()) {
            Request.newMeRequest(session,
                    new Request.GraphUserCallback() {
                        @Override
                        public void onCompleted(GraphUser user,
                                                Response response) {
                            if (user != null) {
                                Toast.makeText(HomeActivity.this, getString(R.string.welcome) + " " + user.getName(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(HomeActivity.this, R.string.message_error, Toast.LENGTH_SHORT).show();
                                logoutFacebook();
                            }
                        }
                    }).executeAsync();
        } else if (sessionState.isClosed()) {
            Toast.makeText(HomeActivity.this, R.string.message_log_out, Toast.LENGTH_SHORT).show();
        }
        */
    }

    private void sharedByFacebook() {

        if (FacebookDialog.canPresentShareDialog(MainActivity.this, FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
            FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(MainActivity.this)
                    .setLink(getString(R.string.url_devf))
                    .setPicture(getString(R.string.ur_devf_logo))
                    .setName(getString(R.string.text_devf_share))
                    .build();
            shareDialog.present();

        } else {

            Bundle params = new Bundle();
            params.putString("name", getString(R.string.text_devf_share));
            params.putString("link", getString(R.string.url_devf));
            params.putString("picture", getString(R.string.ur_devf_logo));

            WebDialog feedDialog =
                    new WebDialog.FeedDialogBuilder(MainActivity.this,
                            Session.getActiveSession(),
                            params).build();

            feedDialog.show();
        }
    }


    private void logoutFacebook() {
        Session session = Session.getActiveSession();
        if (session != null) {
            if (!session.isClosed()) {
                session.closeAndClearTokenInformation();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }

}
