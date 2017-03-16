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

    public void createReport(Report report) {
        _reports.add(report);
        Model.getInstance().addReport(report);
    }

    public ArrayList<Report> pastReports() {
        return Model.getInstance().getReports();
    }
}
