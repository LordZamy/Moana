package moanainc.com.moana.firebase;

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

    public static void loginUser() {
        //TODO:Implement
        //TODO:Method params and return type may/should change
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
