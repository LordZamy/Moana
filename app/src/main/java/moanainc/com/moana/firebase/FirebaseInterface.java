package moanainc.com.moana.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import moanainc.com.moana.model.Report;

/**
 * Created by darrion on 3/30/17.
 */

public class FirebaseInterface {

    private static DatabaseReference mDatabaseReference; //TODO: Need to define globally

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
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("reports").child("availability").setValue(report); //TODO: This only allows one report, we need an array of them
    }

    public static void addPurityReport(Report report) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("reports").child("purity").setValue(report); //TODO: This only allows one report, we need an array of them
    }

    public static void addHistoricalReport(Report report) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("reports").child("historical").setValue(report); //TODO: This only allows one report, we need an array of them
    }

    public static void addSourceReport(Report report) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("reports").child("source").setValue(report); //TODO: This only allows one report, we need an array of them
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
