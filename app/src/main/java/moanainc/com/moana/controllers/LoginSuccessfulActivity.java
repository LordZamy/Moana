package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import moanainc.com.moana.R;

/**
 * Created by josh baldwin on 2/14/2017.
 */

public class LoginSuccessfulActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginsuccessful_activity);
    }
    public void gotoHome(View view) {
        Intent gotoHome = new Intent(getBaseContext(), HomeActivity.class);
        getBaseContext().startActivity(gotoHome);
    }
    public void onLogoutPressed(View view) {
        gotoHome(null);
    }
    public void onSurprisePressed(View view) {

    }
}