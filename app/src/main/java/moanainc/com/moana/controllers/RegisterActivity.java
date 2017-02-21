package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import moanainc.com.moana.R;

/**
 * Created by Micah Terrell on 2/13/2017.
 */

public class RegisterActivity extends AppCompatActivity {


    private User _user;
    private EditText _nameField;
    private EditText _passwordField;
    private EditText _realNameField;
    private TextView nameError;
    private TextView passwordError;
    private Spinner accountSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        _user = new User();
        _nameField = (EditText) findViewById(R.id.editText);
        _passwordField = (EditText) findViewById(R.id.editText2);
        _realNameField = (EditText) findViewById(R.id.editText3);
        nameError = (TextView) findViewById(R.id.textView12);
        passwordError = (TextView) findViewById(R.id.textView13);
        accountSpinner = (Spinner) findViewById(R.id.spinner);

        //set up spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(adapter);
    }

    public void gotoHome(View view) {
        Intent gotoHome = new Intent(getBaseContext(), HomeActivity.class);
        getBaseContext().startActivity(gotoHome);
    }



    /**
     * Button handler for the register button
     *
     * @param view the button
     */
    protected void onRegisterPressed(View view) {
        //Log.d("Edit", "Add user");
        Model model = Model.getInstance();

        String nameInput = _nameField.getText().toString();
        String passwordInput = _passwordField.getText().toString();
        String realNameInput = _realNameField.getText().toString();

        if (validateName(nameInput) && validatePassword(passwordInput)) {
            // add new user to user base
            _user.setUsername(nameInput);
            _user.setPassword(passwordInput);
            _user.setRealName(realNameInput);
            _user.setAccountType((AccountType) accountSpinner.getSelectedItem());
            model.addUser(_user);
            Log.d("RegisterActivity", _user.toString());

            // let user know of registration success
            showConfirmation();
            // move to home
            gotoHome(null);
        }
    }

    private boolean validateName(String nameInput) {
        // check if username string is valid
        if (nameInput.length() < 5) {
            writeNameError("Username needs to be at least 5 characters long.");
            return false;
        } else if (!nameInput.matches("^[a-zA-Z0-9_]*$")) {
            writeNameError("Username can only contain alphanumeric characters.");
            return false;
        } else if (Model.getInstance().userExists(nameInput)) {
            // check if username exists
            writeNameError("Username already exists.");
            return false;
        }

        return true;
    }

    private boolean validatePassword(String passwordInput) {
        if (passwordInput.length() < 8) {
            writePasswordError("Password needs to be at least 8 characters long.");
            return false;
        } else if (!passwordInput.matches("^[a-zA-Z0-9_]*$")) {
            writePasswordError("Password can only contain alphanumeric characters");
            return false;
        }

        return true;
    }

    private void writeNameError(String errorMsg) {
        nameError.setText(errorMsg);
        nameError.setTextColor(0xFFFF0000);
    }

    private void writePasswordError(String errorMsg) {
        passwordError.setText(errorMsg);
        passwordError.setTextColor(0xFFFF0000);
    }


    private void showConfirmation() {
        Toast toast = Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_LONG);
        toast.show();
    }
}
