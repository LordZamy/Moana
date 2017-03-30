package moanainc.com.moana.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import moanainc.com.moana.R;
import moanainc.com.moana.model.user.AccountType;
import moanainc.com.moana.model.user.User;
import moanainc.com.moana.model.Model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Console;

/**
 * Created by Micah Terrell on 2/13/2017.
 */

public class LoginActivity extends AppCompatActivity {

    EditText usernameField;
    EditText passwordField;
    TextView pageTitle;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        usernameField = (EditText) findViewById(R.id.editText);
        passwordField = (EditText) findViewById(R.id.editText2);
        pageTitle = (TextView) findViewById(R.id.textView3);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void goToHome(View view) {
        Intent goToHome = new Intent(getBaseContext(), HomeActivity.class);
        getBaseContext().startActivity(goToHome);
    }

    public void goToApplication(View view) {
        Intent goToApplication = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToApplication);
    }

    public void onLoginPressed(View view) {
        mAuth.signInWithEmailAndPassword(usernameField.getText().toString(), passwordField.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        } else {
                            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            AccountType accountType;

                            if(user == null) {
                                //TODO: fail elegantly
                                Log.d("FIREBASE FAILURE", "RIP");
                            } else {
                                Log.d("FIREBASE GOOD", "HELLO");
                                if(user.getPhotoUrl() == null) {
                                    Log.d("FIREBASE GOOD", "photo is null");
                                }
                            }





                            Log.d("ACCOUNTTYPE", user.getPhotoUrl().toString()); //TODO: Causing a crash on Micah's environment
                            switch (user.getPhotoUrl().toString()) { //TODO: Causing a crash on Micah's environment
                                case "User":
                                    accountType = AccountType.USER;
                                    break;
                                case "Worker":
                                    accountType = AccountType.WORKER;
                                    break;
                                case "Manager":
                                    accountType = AccountType.MANAGER;
                                    break;
                                case "Admin":
                                    accountType = AccountType.ADMIN;
                                    break;
                                default:
                                    accountType = AccountType.USER;
                                    break;
                }
                            Model.getInstance().setCurrentUser(new User(user.getUid(),"", user.getDisplayName(), accountType));
                            Toast toast = Toast.makeText(getApplicationContext(), "Login succeeded", Toast.LENGTH_SHORT);
                            toast.show();
                            goToApplication(null);
                        }
                    }
                });



    }

    public boolean areValidCredentials(String username, String password) {
        return Model.getInstance().getUsers().containsValue(new User(username, password));
    }

    public LoginActivity() { }
    public String getLoginUsername() { return usernameField.getText().toString(); }
    public String getLoginPassword() { return passwordField.getText().toString(); }
}