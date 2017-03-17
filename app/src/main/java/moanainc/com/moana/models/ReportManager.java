package moanainc.com.moana.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by darrion on 2/28/17.
 */

public class ReportManager {

    public static List<String> legalReports = Arrays.asList("Availability", "Purity", "History", "Source");

    ArrayList<Report> _reports = new ArrayList<Report>();
    ArrayList<PurityReport> _purityReports = new ArrayList<>();

    public void createReport(Report report) {
        _reports.add(report);
        Model.getInstance().addReport(report);
    }

    public void createPurityReport(PurityReport report) {
        _purityReports.add(report);
        Model.getInstance().addPurityReport(report);
    }

    public ArrayList<Report> pastReports() {
        return Model.getInstance().getReports();
    }
    public ArrayList<PurityReport> pastPurityReports() { return Model.getInstance().getPurityReports(); }

}
