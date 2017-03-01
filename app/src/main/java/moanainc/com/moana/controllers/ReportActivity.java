package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import java.util.Date;

import moanainc.com.moana.R;
import moanainc.com.moana.models.Model;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void goToWelcome(View view) {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    public void onSubmitReport(View view){
        Model.getInstance().getCurrentUser().createAvailReport("MY REPORT", (new Date()).toString());
        goToWelcome(null);
        Toast toast = Toast.makeText(getApplicationContext(), "Report Created", Toast.LENGTH_LONG);
        toast.show();
    }

}
