package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import moanainc.com.moana.R;

/**
 * Created by josh baldwin on 2/21/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
    }

    public void goToApplication(View view) {
        Intent goToApplication = new Intent(getBaseContext(), ScreenA.class);
        getBaseContext().startActivity(goToApplication);
    }

    public void onSavePressed(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "Failed: Not implemented", Toast.LENGTH_LONG);
        toast.show();
    }
}
