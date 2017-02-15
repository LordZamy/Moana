package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import moanainc.com.moana.R;

/**
 * Created by Micah Terrell on 2/13/2017.
 */

public class RegisterActivity extends AppCompatActivity {


    private User _user;
    private EditText _nameField;
    private EditText _passwordField;
    private TextView pageTitle;
    private TextView nameText;
    private TextView passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        _user = new User();
        _nameField = (EditText) findViewById(R.id.editText);
        _passwordField = (EditText) findViewById(R.id.editText2);
    }

    public void gotoHome(View view) {
        Intent gotoHome = new Intent(getBaseContext(), HomeActivity.class);
        getBaseContext().startActivity(gotoHome);
    }

    public void gotoInvalidPassword(View view) {
        Intent gotoInvalidPassword = new Intent(getBaseContext(), InvalidPasswordActivity.class);
        getBaseContext().startActivity(gotoInvalidPassword);
    }

    /**
     * Button handler for the register button
     *
     * @param view the button
     */
    protected void onRegisterPressed(View view) {
        //Log.d("Edit", "Add user");
        Model model = Model.getInstance();


        if (_nameField.toString().trim().length() == 0) {
            Log.d("empty", "name");
            nameText.setText("Please enter a valid username:");
            nameText.setTextColor(0xFF0000);
        } else if (_passwordField.toString().trim().length() == 0 || !validPassword(_passwordField.getText().toString())) {
            Log.d("invalid", "password");
            passwordText.setText("Please enter a valid password:\n" +
                    "(must be at least 7 alphanumeric characters long with no spaces)");
            passwordText.setTextColor(0xFF0000);
        } else if (validName(_nameField.getText().toString()) && validPassword(_passwordField.getText().toString())) {
            //Log.d("Edit", "Add user");
            _user.setUsername(_nameField.toString());
            _user.setPassword(_passwordField.toString());
            model.addUser(_user);
        }
    }

    private boolean validName(String name) {
        for (User u: Model.getInstance().getUsers()) {
            if (u.getUsername().equals(name)) {
                return false;
            }
        }
        return true;
    }

    private boolean validPassword(String password) {
        for (int i = 0; i < _passwordField.getText().toString().length(); i++) {
            if ((password.charAt(i) < 48) || (password.charAt(i) > 57 && password.charAt(i) < 65)
                    || (password.charAt(i) > 90 && password.charAt(i) < 97)
                    || (password.charAt(i) > 122)) {
                return false;
            }
        }
        return true;
    }
}
