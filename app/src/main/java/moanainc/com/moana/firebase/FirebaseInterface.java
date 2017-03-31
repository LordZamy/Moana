package moanainc.com.moana.firebase;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import moanainc.com.moana.model.Report;

/**
 * Created by darrion on 3/30/17.
 */

public class FirebaseInterface {

    private static DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public static Report getAvailabilityReports() {
        return null;
    }

    public static Report getPurityReports() {
        return null;
    }

    public static Report getHistoryReports() {
        return null;
    }

    public static Report getSourceReports() {
        return null;
    }

    public static void addAvailabilityReport(Report report) {
        mDatabaseReference.child("reports").child("availability").push().setValue(report);
    }

    public static void addPurityReport(Report report) {
        mDatabaseReference.child("reports").child("purity").push().setValue(report);
    }

    public static void addHistoricalReport(Report report) {
        mDatabaseReference.child("reports").child("historical").push().setValue(report);
    }

    public static void addSourceReport(Report report) {
        mDatabaseReference.child("reports").child("source").push().setValue(report);
    }

    public static void loginUser(AppCompatActivity activity, String username, String password) {
        //mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(activity, ) TODO: stuff here
        //TODO:Implement
        //TODO:Method params and return type may/should change
        /*
        //TODO:Pass in callback, and define that callback in the activity that's using this method
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
         */
    }

    public static void registerUser() {
        //TODO:Implement
        //TODO:Method params and return type may/should change
    }

    public static void removeUser() {
        //TODO:Implement
        //TODO:Method params and return type may/should change
    }

}
