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

    EditText nameField;
    EditText passwordField;
    TextView pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        nameField = (EditText) findViewById(R.id.editText);
        passwordField = (EditText) findViewById(R.id.editText2);
        pageTitle = (TextView) findViewById(R.id.textView3);
    }

    public void goToHome(View view) {
        Intent goToHome = new Intent(getBaseContext(), HomeActivity.class);
        getBaseContext().startActivity(goToHome);
    }

    public void onLoginPressed(View view) {
        if (areValidCredentials(nameField.toString(), passwordField.toString())) {
            pageTitle.setText("Welcome, " + nameField.toString());
        } else {
            pageTitle.setText("New phone. Who dis?");
        }
    }

    public boolean areValidCredentials(String username, String password) {
        Model model = Model.getInstance();
        List<User> users = model.getUsers();
        for (User user : users) {
            if (user.getUsername() == username && user.getPassword() == password) {
                return true;
            }
        }
        return false;
    }
}
