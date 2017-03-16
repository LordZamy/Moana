package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import moanainc.com.moana.R;
import moanainc.com.moana.models.AccountType;
import moanainc.com.moana.models.Model;

public class WelcomeActivity extends AppCompatActivity {

    Button _logoutButton;
    Button _editProfile;
    private AccountType _userAccountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _logoutButton = (Button) findViewById(R.id.logoutButton);
        _editProfile = (Button) findViewById(R.id.editButton);
        _userAccountType = Model.getInstance().getCurrentUser().getAccount().getAccountType();
    }

    public void goToHome(View view) {
        Intent goToHome = new Intent(getBaseContext(), HomeActivity.class);
        getBaseContext().startActivity(goToHome);
    }

    public void onLogoutPressed(View view) {
        Model.getInstance().setCurrentUser(null);
        goToHome(null);
    }

    public void onEditProfilePressed(View view) {
        Intent goToProfile = new Intent(getBaseContext(), ProfileActivity.class);
        getBaseContext().startActivity(goToProfile);
    }

    public void onAvailabilityReport(View view) {
        Intent goToCreateReport = new Intent(getBaseContext(), ReportActivity.class);

        if (_userAccountType == AccountType.USER) {
            Toast toast = Toast.makeText(getApplicationContext(), "Users cannot create reports.", Toast.LENGTH_LONG);
            toast.show();
        } else {
            getBaseContext().startActivity(goToCreateReport);
        }
    }

    public void onPurityReport(View view) {
        Intent goToPurityReport = new Intent(getBaseContext(), PurityActivity.class);

        if (_userAccountType == AccountType.USER) {
            Toast toast = Toast.makeText(getApplicationContext(), "Users cannot create reports.", Toast.LENGTH_LONG);
            toast.show();
        } else {
            getBaseContext().startActivity(goToPurityReport);
        }
    }

    public void onViewReports(View view) {
        Intent goToViewReports = new Intent(getBaseContext(), ReportListActivity.class);

        if (_userAccountType != AccountType.WORKER && _userAccountType != AccountType.MANAGER && _userAccountType != AccountType.ADMIN) {
            Toast toast = Toast.makeText(getApplicationContext(), "Only Workers and Managers can access reports.", Toast.LENGTH_LONG);
            toast.show();
        } else {
            getBaseContext().startActivity(goToViewReports);
        }
    }

    public void onViewMap(View view) {
        Intent goToViewMap = new Intent(getBaseContext(), MapsActivity.class);
        getBaseContext().startActivity(goToViewMap);
    }
}
