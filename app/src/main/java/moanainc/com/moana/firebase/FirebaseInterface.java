package moanainc.com.moana.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import moanainc.com.moana.model.Report;

/**
 * Created by darrion on 3/30/17.
 */

public class FirebaseInterface {

    DatabaseReference mDatabaseReference;

    public Report getAvailabilityReports() {
        return null;
    }

    public Report getPurityReports() {
        return null;
    }

    public Report getHistoryReports() {
        return null;
    }

    public Report getSourceReports() {
        return null;
    }

    public void addAvailabilityReport(Report report) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("reports").child("availability").setValue(report);
    }

    public void addPurityReport(Report report) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("reports").child("purity").setValue(report);
    }

    public void addHistoricalReport(Report report) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("reports").child("historical").setValue(report);
    }

    public void addSourceReport(Report report) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("reports").child("source").setValue(report);
    }

    public void loginUser() {
        //TODO:Implement
        //TODO:Method params and return type may/should change
    }

    public void registerUser() {
        //TODO:Implement
        //TODO:Method params and return type may/should change
    }

    public void removeUser() {
        //TODO:Implement
        //TODO:Method params and return type may/should change
    }

}
