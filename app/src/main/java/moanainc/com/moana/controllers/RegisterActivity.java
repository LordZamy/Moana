package moanainc.com.moana.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import moanainc.com.moana.R;

/**
 * Created by Micah Terrell on 2/13/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
    }

    public void gotoHome(View view) {
        Intent gotoHome = new Intent(getBaseContext(), HomeActivity.class);
        getBaseContext().startActivity(gotoHome);
    }

    /**
     * Data from widgets
     */
    private EditText nameField;
    private EditText passwordField;

    /**
     * Data for current user
     */
    private User _user;

    //nameField = (EditText) findViewById(R.id.student_name_input);    //how to get this info
    //passwordField = (EditText) findViewById(R.id.student_id_field);  //how to get this info

    /**
     * Button handler for the register button
     * @param view the button
     */
    protected void onRegisterPressed(View view) {
        Log.d("Edit", "Add user");
        Model model = Model.getInstance();

        /*if (nameField and passwordField not empty){
            _user.setUsername(nameField);
            _user.setPassword(passwordField);
            _users.add(_user)
            go to registration success page
        } else if (nameField is empty) {
            registration failed page
            enter username
        } else if (passwordField is empty) {
            registration failed page
            enter password
        }*/
    }
}
