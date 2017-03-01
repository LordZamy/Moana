package moanainc.com.moana.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darrion on 2/28/17.
 */

public class ReportManager {

    List<Report> _reports = new ArrayList<Report>();;

    public void createReport(Report report) {
        _reports.add(report);
    }

    public List<Report> pastReports() {
        return _reports;
    }
}
