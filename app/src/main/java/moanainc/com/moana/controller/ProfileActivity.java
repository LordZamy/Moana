package moanainc.com.moana.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import moanainc.com.moana.R;
import moanainc.com.moana.model.user.Account;
import moanainc.com.moana.model.Model;
import moanainc.com.moana.model.user.AccountType;

/**
 * Created by josh baldwin on 2/21/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    private EditText _realNameField;
    private EditText _emailAddressField;
    private EditText _homeAddressField;
    private EditText _accountTypeField;


    private final Account u = Model.getInstance().getCurrentUser().getAccount();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        _realNameField = (EditText) findViewById(R.id.editText3);
        _emailAddressField = (EditText) findViewById(R.id.editText);
        _homeAddressField = (EditText) findViewById(R.id.editText2);
        _accountTypeField = (EditText) findViewById(R.id.editText4);

        _realNameField.setText(u.getName());
        _homeAddressField.setText(u.getHomeAddress());
        _emailAddressField.setText(u.getEmailAddress());
        _accountTypeField.setText(u.getAccountType().toString());

    }

    public void goToApplication(View view) {
        Intent goToApplication = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToApplication);
    }

    public void onSavePressed(View view){
        u.setEmailAddress(_emailAddressField.getText().toString());
        u.setHomeAddress(_homeAddressField.getText().toString());
        u.setName(_realNameField.getText().toString());
        u.setAccountType(AccountType.valueOf(_accountTypeField.getText().toString().toUpperCase()));
        goToApplication(null);
    }
}
