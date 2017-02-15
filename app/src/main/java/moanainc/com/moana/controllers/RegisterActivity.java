package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import moanainc.com.moana.R;

/**
 * Created by Micah Terrell on 2/13/2017.
 */

public class RegisterActivity extends AppCompatActivity {


    private User _user;
    private EditText _nameField;
    private EditText _passwordField;

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

    /**
     * Button handler for the register button
     * @param view the button
     */
    protected void onRegisterPressed(View view) {
        Log.d("Edit", "Add user");
        Model model = Model.getInstance();

        /*if (_nameField and _passwordField not empty){
            _user.setUsername(_nameField);
            _user.setPassword(_passwordField);
            _users.add(_user)
            go to registration success page
        } else if (_nameField is empty) {
            registration failed page
            enter username
        } else if (_passwordField is empty) {
            registration failed page
            enter password
        }*/

        if (validName(_nameField.getText().toString()) && validPassword(_passwordField.getText().toString())) {
            _user.setUsername(_nameField.toString());
            _user.setPassword(_nameField.toString());
            model.addUser(_user);
        } else if (_nameField.toString().trim().length() == 0) {
            // go to registration failed activity
        } else if (_passwordField.toString().trim().length() == 0) {
            // go to registration failed activity
        }
    }

    private boolean validName(String name) {
        return true;
    }

    private boolean validPassword(String name) {
        return true;
    }
}
