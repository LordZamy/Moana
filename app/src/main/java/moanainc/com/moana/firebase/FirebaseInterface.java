package moanainc.com.moana.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.report.AvailReport;
import moanainc.com.moana.model.report.HistoryReport;
import moanainc.com.moana.model.report.PurityReport;
import moanainc.com.moana.model.report.SourceReport;

/**
 * Created by darrion on 3/30/17.
 */

public class FirebaseInterface {

    private static final DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private static ArrayList<Report> availibilityReports;
    private static ArrayList<Report> purityReports;
    private static ArrayList<Report> historyReports;
    private static ArrayList<Report> sourceReports;

    private static boolean reportError = false;

    private static final ValueEventListener availabilityReportListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.

            availibilityReports.clear();
            for(DataSnapshot child : dataSnapshot.getChildren()) {
                try {
                    availibilityReports.add(child.getValue(AvailReport.class));
                } catch (DatabaseException err) {
                    reportError = true;
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w("TAG", "Failed to read value.", error.toException());
        }
    };

    private static final ValueEventListener purityReportListener  = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.

            purityReports.clear();
            for(DataSnapshot child : dataSnapshot.getChildren()) {
                try {
                    purityReports.add(child.getValue(PurityReport.class));
                } catch (DatabaseException err) {
                    reportError = true;
                }

            }
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w("TAG", "Failed to read value.", error.toException());
        }
    };

    private static final ValueEventListener historyReportListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.

            //ArrayList<HistoryReport> newReportList = new ArrayList<>();

            historyReports.clear();
            for(DataSnapshot child : dataSnapshot.getChildren()) {
                try {
                    historyReports.add(child.getValue(HistoryReport.class));
                } catch (DatabaseException err) {
                    reportError = true;
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w("TAG", "Failed to read value.", error.toException());
        }
    };

    private static final ValueEventListener sourceReportListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.

            sourceReports.clear();
            for(DataSnapshot child : dataSnapshot.getChildren()) {
                try {
                    sourceReports.add(child.getValue(SourceReport.class));
                } catch (DatabaseException err) {
                    reportError = true;
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w("TAG", "Failed to read value.", error.toException());
        }
    };


    private static void startData() {
        //Instatiate the report lists
        availibilityReports = new ArrayList<>();
        purityReports = new ArrayList<>();
        historyReports = new ArrayList<>();
        sourceReports = new ArrayList<>();

        //Log.d("LOGIN", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        //Define all of the report listeners

        //Add all of the listeners
        mDatabaseReference.child("reports").child("availability").addValueEventListener(availabilityReportListener);
        mDatabaseReference.child("reports").child("purity").addValueEventListener(purityReportListener);
        mDatabaseReference.child("reports").child("historical").addValueEventListener(historyReportListener);
        mDatabaseReference.child("reports").child("source").addValueEventListener(sourceReportListener);


    }

    private static void stopData() {
        //Instatiate the report lists
        availibilityReports = new ArrayList<>();
        purityReports = new ArrayList<>();
        historyReports = new ArrayList<>();
        sourceReports = new ArrayList<>();

        reportError = false;

        //Log.d("LOGIN", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        //Define all of the report listeners

        mDatabaseReference.child("reports").child("availability").removeEventListener(availabilityReportListener);
        mDatabaseReference.child("reports").child("purity").removeEventListener(purityReportListener);
        mDatabaseReference.child("reports").child("historical").removeEventListener(historyReportListener);
        mDatabaseReference.child("reports").child("source").removeEventListener(sourceReportListener);



    }

    public static ArrayList<Report> getAvailabilityReports() {
        return availibilityReports;
    }

    public static ArrayList<Report> getPurityReports() {
        return purityReports;
    }

    public static ArrayList<Report> getHistoryReports() {
        return historyReports;
    }

    public static ArrayList<Report> getSourceReports() {
        return sourceReports;
    }

    public static ArrayList<Report> getAllReports() {
        ArrayList<Report> allReports = new ArrayList<>();
        allReports.addAll(availibilityReports);
        allReports.addAll(purityReports);
        allReports.addAll(historyReports);
        allReports.addAll(sourceReports);
        return allReports;
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

    public static void loginUser(OnCompleteListener listener, String username, String password) {
        final Task task = mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(listener).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startData();
                }
            }
        });
    }

    public static void logout() {
        stopData();
        FirebaseAuth.getInstance().signOut();
    }

    public static void registerUser(OnCompleteListener listener, String username, String password) {
        final Task task = mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(listener);
    }

    public static boolean getReportError() {
        return reportError;
    }
}
