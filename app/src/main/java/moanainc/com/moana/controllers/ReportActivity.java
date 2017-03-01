package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Date;

import moanainc.com.moana.R;
import moanainc.com.moana.models.AvailReport;
import moanainc.com.moana.models.Model;
import moanainc.com.moana.models.ReportManager;

public class ReportActivity extends AppCompatActivity {

    private EditText _reportName;
    private EditText _reportStatus;
    private Spinner _statusSpinner;
    private ReportManager _reportManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _reportName = (EditText) findViewById(R.id.editText5);
        _reportStatus = (EditText) findViewById(R.id.editText6);
        _statusSpinner = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AvailReport.legalStatus);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _statusSpinner.setAdapter(adapter);
    }

    public void goToWelcome(View view) {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    public void onSubmitReport(View view){
        String nameInput = _reportName.getText().toString();
        String statusInput = _reportStatus.getText().toString();
        //AvailReport _availReport = Model.getInstance().getCurrentUser().createAvailReport(nameInput, (new Date()).toString(), statusInput);
        //_reportManager.createReport(_availReport);

        goToWelcome(null);
        Toast toast = Toast.makeText(getApplicationContext(), "Report Created", Toast.LENGTH_LONG);
        toast.show();
    }

    public void onCancelPressed(View view) {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

}
