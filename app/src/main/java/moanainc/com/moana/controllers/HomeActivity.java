package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import moanainc.com.moana.R;

/**
 * Created by Micah Terrell on 2/13/2017.
 */

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
    }

    public void gotoLogin(View view) {
        Intent gotoLogin = new Intent(getBaseContext(), LoginActivity.class);
        getBaseContext().startActivity(gotoLogin);
    }

    public void gotoRegister(View view) {
        Intent gotoRegister = new Intent(getBaseContext(), RegisterActivity.class);
        getBaseContext().startActivity(gotoRegister);
    }

}
