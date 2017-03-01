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

import moanainc.com.moana.R;
import moanainc.com.moana.models.User;
import moanainc.com.moana.models.Worker;
import moanainc.com.moana.models.Manager;
import moanainc.com.moana.models.Admin;
import moanainc.com.moana.models.AccountType;
import moanainc.com.moana.models.Model;

/**
 * Created by Micah Terrell on 2/13/2017.
 */

public class RegisterActivity extends AppCompatActivity {


    private User _user;
    private EditText _usernameField;
    private EditText _passwordField;
    private EditText _nameField;
    private TextView usernameError;
    private TextView passwordError;
    private Spinner accountSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        _user = new User();
        _usernameField = (EditText) findViewById(R.id.editText);
        _passwordField = (EditText) findViewById(R.id.editText2);
        _nameField = (EditText) findViewById(R.id.editText3);
        usernameError = (TextView) findViewById(R.id.textView12);
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

        String usernameInput = _usernameField.getText().toString();
        String passwordInput = _passwordField.getText().toString();
        String nameInput = _nameField.getText().toString();

        if (validateName(usernameInput) && validatePassword(passwordInput)) {
            // add new user to user base
            switch((AccountType) accountSpinner.getSelectedItem()) {
                case USER:
                    _user = new User();
                    break;
                case WORKER:
                    _user = new Worker();
                    break;
                case MANAGER:
                    _user = new Manager();
                    break;
                case ADMIN:
                    _user = new Admin();
                    break;
                default:
                    _user = new User();
            }
            _user.getAccount().setUsername(usernameInput);
            _user.getAccount().setPassword(passwordInput);
            _user.getAccount().setName(nameInput);
            _user.getAccount().setAccountType((AccountType) accountSpinner.getSelectedItem());
            model.addUser(_user);
            Log.d("RegisterActivity", _user.getAccount().toString());

            // let user know of registration success
            showConfirmation();
            // move to home
            gotoHome(null);
        }
    }

    private boolean validateName(String usernameInput) {
        // check if username string is valid
        if (usernameInput.length() < 5) {
            writeNameError("Username needs to be at least 5 characters long.");
            return false;
        } else if (!usernameInput.matches("^[a-zA-Z0-9_]*$")) {
            writeNameError("Username can only contain alphanumeric characters.");
            return false;
        } else if (Model.getInstance().userExists(usernameInput)) {
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
        usernameError.setText(errorMsg);
        usernameError.setTextColor(0xFFFF0000);
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
