package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import moanainc.com.moana.R;

/**
 * Created by josh baldwin on 2/21/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
    }

    public void goToApplication(View view) {
        Intent goToApplication = new Intent(getBaseContext(), ScreenA.class);
        getBaseContext().startActivity(goToApplication);
    }
}
