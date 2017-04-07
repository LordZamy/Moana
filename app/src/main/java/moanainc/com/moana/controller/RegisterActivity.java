package moanainc.com.moana.controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import moanainc.com.moana.R;
import moanainc.com.moana.model.user.User;
import moanainc.com.moana.model.user.AccountType;
import moanainc.com.moana.model.Model;

/**
 * Created by Micah Terrell on 2/13/2017.
 */

public class RegisterActivity extends AppCompatActivity {


    private User _user;
    private TextView mTitleView;
    private EditText _usernameField;
    private EditText _passwordField;
    private EditText _nameField;
    private TextView _usernamePrompt;
    private TextView _emailPrompt;
    private TextView _passwordPrompt;
    private Button button;
    private Button button2;
    private TextView usernameError;
    private TextView passwordError;
    private Spinner accountSpinner;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        mTitleView = (TextView) findViewById(R.id.textView3);
        _user = new User();
        _usernameField = (EditText) findViewById(R.id.editText);
        _passwordField = (EditText) findViewById(R.id.editText2);
        _nameField = (EditText) findViewById(R.id.editText3);
        _emailPrompt = (TextView) findViewById(R.id.emailPrompt);
        _passwordPrompt = (TextView) findViewById(R.id.passwordPrompt);
        _usernamePrompt = (TextView) findViewById(R.id.usernamePrompt);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        usernameError = (TextView) findViewById(R.id.textView12);
        passwordError = (TextView) findViewById(R.id.textView13);
        accountSpinner = (Spinner) findViewById(R.id.spinner);

        //set up spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(adapter);

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

        Typeface disney_font = Typeface.createFromAsset(getAssets(),  "fonts/disneyui.ttf");
        Typeface varela_round_font = Typeface.createFromAsset(getAssets(),  "fonts/VarelaRound-Regular.ttf");
        mTitleView.setTypeface(disney_font);
        _usernamePrompt.setTypeface(varela_round_font);
        _emailPrompt.setTypeface(varela_round_font);
        _passwordPrompt.setTypeface(varela_round_font);
        button.setTypeface(varela_round_font);
        button2.setTypeface(varela_round_font);
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

        final String usernameInput = _usernameField.getText().toString();
        final String passwordInput = _passwordField.getText().toString();
        final String nameInput = _nameField.getText().toString();

        mAuth.createUserWithEmailAndPassword(usernameInput, passwordInput)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Failed",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(nameInput)
                                    .setPhotoUri(Uri.parse(accountSpinner.getSelectedItem().toString()))
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("TAG", "user profile updated");
                                                // let user know of registration success
                                                showConfirmation();
                                                // move to home
                                                gotoHome(null);

                                            }
                                        }
                                    });
                        }

                    }
                });

    }

    public boolean validateName(String usernameInput) {
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
