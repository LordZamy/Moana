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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

/*
 * Created by Micah Terrell on 2/13/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private TextView emailPrompt;
    private TextView passwordPrompt;
    private Button loginButton;
    private Button goBackButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        usernameField = (EditText) findViewById(R.id.editText);
        passwordField = (EditText) findViewById(R.id.editText2);
        emailPrompt = (TextView) findViewById(R.id.emailPrompt);
        passwordPrompt = (TextView) findViewById(R.id.passwordPrompt);
        TextView pageTitle = (TextView) findViewById(R.id.textView3);
        loginButton = (Button) findViewById(R.id.loginButton);
        goBackButton = (Button) findViewById(R.id.goBackButton);

        Typeface disney_font = Typeface.createFromAsset(getAssets(),  "fonts/disneyui.ttf");
        Typeface varela_round_font = Typeface.createFromAsset(getAssets(),  "fonts/VarelaRound-Regular.ttf");
        pageTitle.setTypeface(disney_font);
        emailPrompt.setTypeface(varela_round_font);
        passwordPrompt.setTypeface(varela_round_font);
        loginButton.setTypeface(varela_round_font);
        goBackButton.setTypeface(varela_round_font);
        ((Button) findViewById(R.id.resetButton)).setTypeface(varela_round_font);

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

        if (AccessToken.getCurrentAccessToken() != null) {
            Button continueLogin = (Button) findViewById(R.id.continueButton);
            continueLogin.setVisibility(View.VISIBLE);
            continueLogin.setText("Continue as " + Profile.getCurrentProfile().getFirstName() + "...");
        } else {
            Button continueLogin = (Button) findViewById(R.id.continueButton);
            continueLogin.setVisibility(View.INVISIBLE);
        }

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("", "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("", "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("", "facebook:onError", error);
                // ...
            }
        });

        LoginButton loginButton = (LoginButton) findViewById(R.id.button_facebook_login);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("", "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("", "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("", "facebook:onError", error);
                // ...
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
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
                    try {
                        throw task.getException();
                    } catch(FirebaseAuthInvalidUserException invalidUser) {
                        Toast.makeText(LoginActivity.this, "Login failed: Invalid email", Toast.LENGTH_SHORT).show();
                    } catch(FirebaseAuthInvalidCredentialsException invalidCredentials) {
                        Toast.makeText(LoginActivity.this, "Login failed: Wrong Password", Toast.LENGTH_SHORT).show();
                    } catch(Exception e) {
                        Toast.makeText(LoginActivity.this, "Bro... do you even lift?", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    String accountString;
                    try{
                        accountString = user.getPhotoUrl().toString();
                    } catch (NullPointerException err) {
                        accountString = "User";
                    }
                    Log.d("ACCOUNT TYPE:", accountString);

                    switch (accountString) {
                        case "User":
                            Model.getInstance().setCurrentUser(new User(user.getUid(), "", user.getDisplayName(), AccountType.USER));
                            break;
                        case "Worker":
                            Model.getInstance().setCurrentUser(new Worker(user.getUid(), user.getDisplayName(), AccountType.WORKER));
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
                    goToApplication(null);
                }
            }
        };
        //call the login method
        if(areValidCredentials(usernameField.getText().toString(), passwordField.getText().toString())) {
            FirebaseInterface.loginUser(listener, usernameField.getText().toString(), passwordField.getText().toString());
        } else {
            Toast.makeText(LoginActivity.this, "Why would you click login, when you didn't fill everything out?", Toast.LENGTH_LONG).show();
        }
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("", "handleFacebookAccessToken:" + token);

        OnCompleteListener listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("TAG", "signInWithFacebook:onComplete:" + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Log.w("TAG", "signInWithFacebook:failed", task.getException());
                    try {
                        throw task.getException();
                    } catch(FirebaseAuthUserCollisionException userCollision) {
                        Toast.makeText(LoginActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    } catch(FirebaseAuthInvalidUserException invalidUser) {
                        Toast.makeText(LoginActivity.this, "Login failed: Invalid email", Toast.LENGTH_SHORT).show();
                    } catch(FirebaseAuthInvalidCredentialsException invalidCredentials) {
                        Toast.makeText(LoginActivity.this, "Login failed: Wrong Password", Toast.LENGTH_SHORT).show();
                    } catch(Exception e) {
                        Toast.makeText(LoginActivity.this, "Unknown Facebook Error", Toast.LENGTH_SHORT).show();
                    }
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
                            Model.getInstance().setCurrentUser(new Worker(user.getUid(), user.getDisplayName(), AccountType.WORKER));
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
                    goToApplication(null);
                }
            }
        };

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        FirebaseInterface.facebookLoginUser(listener, credential);
    }

    public void onResetPassword(View view) {
        if(!usernameField.getText().toString().isEmpty()){
            OnCompleteListener listener = new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Email error, contact admin", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            FirebaseInterface.sendResetEmail(listener, usernameField.getText().toString());
        } else {
            Toast.makeText(LoginActivity.this, "Enter your email in the email box above", Toast.LENGTH_LONG).show();
        }
    }

    public void onContinue(View view) {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }


    public boolean areValidCredentials(String username, String password) {
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            return true;
        }
        return false;
    }

    public LoginActivity() { }
    public String getLoginUsername() { return usernameField.getText().toString(); }
    public String getLoginPassword() { return passwordField.getText().toString(); }
}
