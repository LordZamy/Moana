package moanainc.com.moana.firebase;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.report.AvailReport;
import moanainc.com.moana.model.report.HistoryReport;
import moanainc.com.moana.model.report.PurityReport;
import moanainc.com.moana.model.report.SourceReport;

/**
 * Created by darrion on 3/30/17.
 */

public class FirebaseInterface {

    private static DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private ValueEventListener availabilityReportListener;
    private ValueEventListener purityReportListener;
    private ValueEventListener historyReportListener;
    private ValueEventListener sourceReportListener;

    private ArrayList<AvailReport> availibilityReports;
    private ArrayList<PurityReport> purityReports;
    private ArrayList<HistoryReport> historyReports;
    private ArrayList<SourceReport> sourceReports;


    public FirebaseInterface() {
        //Instatiate the report lists
        availibilityReports = new ArrayList<>();
        purityReports = new ArrayList<>();
        historyReports = new ArrayList<>();
        sourceReports = new ArrayList<>();
        Log.d("LOGIN", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        //Define all of the report listeners
        availabilityReportListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                ArrayList<AvailReport> newReportList = new ArrayList<>();

                for(DataSnapshot child : dataSnapshot.getChildren()) {
                   //TODO:Parse the snapshot reports and add them into the new report array list
                }

                availibilityReports = newReportList;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        };

        purityReportListener  = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                ArrayList<PurityReport> newReportList = new ArrayList<>();

                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    //TODO:Parse the snapshot reports and add them into the new report array list
                }

                purityReports = newReportList;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        };

        historyReportListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                ArrayList<HistoryReport> newReportList = new ArrayList<>();

                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    //TODO:Parse the snapshot reports and add them into the new report array list
                }

                historyReports = newReportList;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        };

        sourceReportListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //TODO: Need to take the snapshot data and put it into the respective array list for this report type
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                ArrayList<SourceReport> newReportList = new ArrayList<>();

                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    //TODO:Parse the snapshot reports and add them into the new report array list
                }

                sourceReports = newReportList;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        };

        //Add all of the listeners //TODO:These listeners may need to be pointed at another child deeper to see the Firebase list updates
        mDatabaseReference.child("reports").child("availability").addValueEventListener(availabilityReportListener);
        mDatabaseReference.child("reports").child("purity").addValueEventListener(purityReportListener);
        mDatabaseReference.child("reports").child("historical").addValueEventListener(historyReportListener);
        mDatabaseReference.child("reports").child("source").addValueEventListener(sourceReportListener);


    }

    public ArrayList<AvailReport> getAvailabilityReports() {
        /*
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        };
        mDatabaseReference.addValueEventListener(listener);
        return null;
        */
        return availibilityReports;
    }

    public ArrayList<PurityReport> getPurityReports() {
        return purityReports;
    }

    public ArrayList<HistoryReport> getHistoryReports() {
        return historyReports;
    }

    public ArrayList<SourceReport> getSourceReports() {
        return sourceReports;
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
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(listener);
    }

    public static void registerUser(OnCompleteListener listener, String username, String password) {
        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(listener);
    }

    public static void removeUser() {
        //TODO:Implement
        //TODO:Method params and return type may/should change

        //TODO: Perhaps use the firebase method User.delete()?
    }

}
