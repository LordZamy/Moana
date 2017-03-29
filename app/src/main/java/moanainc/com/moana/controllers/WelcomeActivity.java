package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import moanainc.com.moana.R;
import moanainc.com.moana.models.AccountType;
import moanainc.com.moana.models.Model;
import moanainc.com.moana.models.ReportManager;

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

    public void onNewReport(View view) {
        final ListPopupWindow popup = new ListPopupWindow(this);
        popup.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, ReportManager.legalReports));
        popup.setAnchorView(findViewById(R.id.reportButton));
        popup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goToCreateReport;
                switch(ReportManager.legalReports.get(position)){
                    case "Availability":
                        goToCreateReport = new Intent(getBaseContext(), AvailabilityActivity.class);
                        getBaseContext().startActivity(goToCreateReport);
                        break;
                    case "Purity":
                        if(_userAccountType == AccountType.USER || _userAccountType == AccountType.ADMIN) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Users and Admins cannot create purity reports.", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            goToCreateReport = new Intent(getBaseContext(), PurityActivity.class);
                            getBaseContext().startActivity(goToCreateReport);
                        }
                        break;
                    case "History":
                        break;
                    case "Source":
                        break;
                }
                popup.dismiss();
            }
        });
        popup.show();
    }

    public void onViewReports(View view) {
        final ListPopupWindow popup = new ListPopupWindow(this);
        popup.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, ReportManager.legalReports));
        popup.setAnchorView(findViewById(R.id.viewButton));
        popup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goToCreateReport;
                switch(ReportManager.legalReports.get(position)){
                    case "Availability":
                        goToCreateReport = new Intent(getBaseContext(), ReportListActivity.class);
                        getBaseContext().startActivity(goToCreateReport);
                        break;
                    case "Purity":
                        if(_userAccountType == AccountType.USER || _userAccountType == AccountType.ADMIN) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Users and Admins cannot view purity reports.", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            goToCreateReport = new Intent(getBaseContext(), PurityReportListActivity.class);
                            getBaseContext().startActivity(goToCreateReport);
                        }
                        break;
                    case "History":
                        break;
                    case "Source":
                        break;
                }
                popup.dismiss();
            }
        });
        popup.show();
    }

    public void onViewMap(View view) {
        Intent goToViewMap = new Intent(getBaseContext(), MapsActivity.class);
        getBaseContext().startActivity(goToViewMap);
    }
}
