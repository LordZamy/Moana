package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import moanainc.com.moana.R;
import moanainc.com.moana.models.AccountType;
import moanainc.com.moana.models.Model;
import moanainc.com.moana.models.ReportManager;

/**
 * Created by josh baldwin on 3/16/2017.
 */

public class ChooseReportActivity extends AppCompatActivity {

    private Spinner _statusSpinner;
    private ReportManager _reportManager;
    private AccountType _userAccountType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosereport_activity);

        _statusSpinner = (Spinner) findViewById(R.id.spinner3);
        _userAccountType = Model.getInstance().getCurrentUser().getAccount().getAccountType();


        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, _reportManager.legalReports);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _statusSpinner.setAdapter(adapter);
    }

    public void onCancelPressed(View view) {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    public void onSelectPressed(View view) {
        Intent goToCreateReport = new Intent(getBaseContext(), AvailabilityActivity.class);
        Intent goToPurityReport = new Intent(getBaseContext(), PurityActivity.class);

        String statusInput = _statusSpinner.getSelectedItem().toString();
        if (statusInput.equals("Purity") && (_userAccountType == AccountType.USER || _userAccountType == AccountType.ADMIN)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Users and Admins cannot create purity reports.", Toast.LENGTH_LONG);
            toast.show();
        } else if (statusInput.equals("Purity") && (_userAccountType == AccountType.WORKER || _userAccountType == AccountType.MANAGER)) {
            getBaseContext().startActivity(goToPurityReport);
        } else if (statusInput.equals("Availability")) {
            getBaseContext().startActivity(goToCreateReport);
        }
    }
}
