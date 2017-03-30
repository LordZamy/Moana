package moanainc.com.moana.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import moanainc.com.moana.model.report.PurityReport;

/**
 * Created by darrion on 2/28/17.
 */

public class ReportManager {

    private DatabaseReference mDatabaseReference;

    public static List<String> legalReports = Arrays.asList("Availability", "Purity", "History", "Source");

    public void createReport(Report report) {
        // TODO: Call Firebase interface.
        FirebaseDatabase.getInstance().getReference();

        //Model.getInstance().addReport(report);
    }

    public void createPurityReport(PurityReport report) {
        //_purityReports.add(report);
        Model.getInstance().addPurityReport(report);
    }

    public ArrayList<Report> pastReports() {
        return Model.getInstance().getReports();
    }
    public ArrayList<PurityReport> pastPurityReports() { return Model.getInstance().getPurityReports(); }

}
