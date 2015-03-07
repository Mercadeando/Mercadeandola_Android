package com.example.loginface.loginface;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        requestJson();
    }

    private void requestJson() {

        Session session = Session.getActiveSession();
        Request.newMeRequest(session,
                new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(GraphUser user,
                                            Response response) {
                        if (user != null) {
                            TextView textViewJson = (TextView) findViewById(R.id.textViewJson);

                            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                            String stringJson = prettyGson.toJson(user.getInnerJSONObject());
                            Log.d("TAG", stringJson);
                            textViewJson.setText(stringJson);
                        } else {
                            Toast.makeText(JsonActivity.this, R.string.message_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                }).executeAsync();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
