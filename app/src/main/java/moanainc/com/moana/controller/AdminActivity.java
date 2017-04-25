package moanainc.com.moana.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import moanainc.com.moana.R;
import moanainc.com.moana.firebase.FirebaseInterface;
import moanainc.com.moana.model.Model;
import moanainc.com.moana.model.user.Account;
import moanainc.com.moana.model.user.AccountType;

/**
 * Created by reecedantin on 4/24/2017.
 */

public class AdminActivity extends AppCompatActivity {

    private EditText _emailField;


    private final Account u = Model.getInstance().getCurrentUser().getAccount();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        _emailField = (EditText) findViewById(R.id.editText);
    }

    public void goToApplication(View view) {
        Intent goToApplication = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToApplication);
    }

    public void onSavePressed(View view){
        OnCompleteListener listener = new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(AdminActivity.this, "Email sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminActivity.this, "Email error", Toast.LENGTH_SHORT).show();
                }

            }
        };
        FirebaseInterface.sendResetEmail(listener, _emailField.getText().toString());
    }
}
