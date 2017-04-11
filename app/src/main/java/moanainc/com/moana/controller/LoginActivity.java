package moanainc.com.moana.controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import moanainc.com.moana.R;
import moanainc.com.moana.firebase.FirebaseInterface;
import moanainc.com.moana.model.user.AccountType;
import moanainc.com.moana.model.user.Admin;
import moanainc.com.moana.model.user.Manager;
import moanainc.com.moana.model.user.User;
import moanainc.com.moana.model.Model;
import moanainc.com.moana.model.user.Worker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
 * Created by Micah Terrell on 2/13/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private TextView pageTitle;
    private TextView emailPrompt;
    private TextView passwordPrompt;
    private Button loginButton;
    private Button goBackButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        usernameField = (EditText) findViewById(R.id.editText);
        passwordField = (EditText) findViewById(R.id.editText2);
        emailPrompt = (TextView) findViewById(R.id.emailPrompt);
        passwordPrompt = (TextView) findViewById(R.id.passwordPrompt);
        pageTitle = (TextView) findViewById(R.id.textView3);
        loginButton = (Button) findViewById(R.id.loginButton);
        goBackButton = (Button) findViewById(R.id.goBackButton);

        Typeface disney_font = Typeface.createFromAsset(getAssets(),  "fonts/disneyui.ttf");
        Typeface varela_round_font = Typeface.createFromAsset(getAssets(),  "fonts/VarelaRound-Regular.ttf");
        pageTitle.setTypeface(disney_font);
        emailPrompt.setTypeface(varela_round_font);
        passwordPrompt.setTypeface(varela_round_font);
        loginButton.setTypeface(varela_round_font);
        goBackButton.setTypeface(varela_round_font);

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

        /*START TEST SECTION*/
        //FirebaseInterface fbi = new FirebaseInterface();
        //Log.d("FB", "GOT HERE 1");

        //fbi.getAvailabilityReports();


        /*END TEST SECTION*/
        //Define the callback
        OnCompleteListener listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Log.w("TAG", "signInWithEmail:failed", task.getException());
                    Toast.makeText(LoginActivity.this, "Login failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                } else {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    String accountString;
                    try{
                        accountString = user.getPhotoUrl().toString();
                    } catch (NullPointerException err) {
                        accountString = "User";
                    }

                    switch (accountString) {
                        case "User":
                            Model.getInstance().setCurrentUser(new User(user.getUid(), "", user.getDisplayName(), AccountType.USER));
                            break;
                        case "Worker":
                            Model.getInstance().setCurrentUser(new Worker(user.getUid(), "", user.getDisplayName(), AccountType.WORKER));
                            break;
                        case "Manager":
                            Model.getInstance().setCurrentUser(new Manager(user.getUid(), user.getDisplayName()));
                            break;
                        case "Admin":
                            Model.getInstance().setCurrentUser(new Admin(user.getUid(), user.getDisplayName()));
                            break;
                        default:
                            Model.getInstance().setCurrentUser(new User(user.getUid(), "", user.getDisplayName(), AccountType.USER));
                            break;
                    }
                    Toast toast = Toast.makeText(getApplicationContext(), "Login succeeded", Toast.LENGTH_SHORT);
                    toast.show();
                    goToApplication(null);}
            }
        };
        //call the login method
        FirebaseInterface.loginUser(listener, usernameField.getText().toString(), passwordField.getText().toString());
    }

    public boolean areValidCredentials(String username, String password) {
        return Model.getInstance().getUsers().containsValue(new User(username, password));
    }

    public LoginActivity() { }
    public String getLoginUsername() { return usernameField.getText().toString(); }
    public String getLoginPassword() { return passwordField.getText().toString(); }
}
