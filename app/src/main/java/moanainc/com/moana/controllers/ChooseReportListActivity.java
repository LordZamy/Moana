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

public class ChooseReportListActivity extends AppCompatActivity {

    private Spinner _reportSpinner;
    private ReportManager _reportManager;
    private AccountType _userAccountType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_report_list);

        _reportSpinner = (Spinner) findViewById(R.id.spinner4);
        _userAccountType = Model.getInstance().getCurrentUser().getAccount().getAccountType();


        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, _reportManager.legalReports);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _reportSpinner.setAdapter(adapter);
    }

    public void onCancelPressed(View view) {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    public void onSelectPressed(View view) {
        Intent goToAvailReportList = new Intent(getBaseContext(), ReportListActivity.class); ////AvailReportList.class
        Intent goToPurityReportList = new Intent(getBaseContext(), PurityReportListActivity.class); ////PurityReportList.class

        String statusInput = _reportSpinner.getSelectedItem().toString();
        if (statusInput.equals("Purity") && (_userAccountType != AccountType.MANAGER)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Only Managers can view purity reports.", Toast.LENGTH_LONG);
            toast.show();
        } else if (statusInput.equals("Purity") && (_userAccountType == AccountType.MANAGER)) {
            getBaseContext().startActivity(goToPurityReportList);
        } else if (statusInput.equals("Availability")) {
            getBaseContext().startActivity(goToAvailReportList);
        }
    }
}
