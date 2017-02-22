package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import moanainc.com.moana.R;

/**
 * Created by Micah Terrell on 2/13/2017.
 */

public class LoginActivity extends AppCompatActivity {

    EditText usernameField;
    EditText passwordField;
    TextView pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        usernameField = (EditText) findViewById(R.id.editText);
        passwordField = (EditText) findViewById(R.id.editText2);
        pageTitle = (TextView) findViewById(R.id.textView3);
    }

    public void goToHome(View view) {
        Intent goToHome = new Intent(getBaseContext(), HomeActivity.class);
        getBaseContext().startActivity(goToHome);
    }

    public void goToApplication(View view) {
        Intent goToApplication = new Intent(getBaseContext(), ScreenA.class);
        getBaseContext().startActivity(goToApplication);
    }

    public void onLoginPressed(View view) {
        if (areValidCredentials(usernameField.getText().toString(), passwordField.getText().toString())) {
            Model.getInstance().setCurrentUser(Model.getInstance().getUsers().get(usernameField.getText().toString()));
            Toast toast = Toast.makeText(getApplicationContext(), "Login succeeded", Toast.LENGTH_SHORT);
            toast.show();
            goToApplication(null);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public boolean areValidCredentials(String username, String password) {
        return Model.getInstance().getUsers().containsValue(new User(username, password));
    }

    public LoginActivity() { }
    public String getLoginUsername() { return usernameField.getText().toString(); }
    public String getLoginPassword() { return passwordField.getText().toString(); }
}
