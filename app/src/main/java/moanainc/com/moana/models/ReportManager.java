package moanainc.com.moana.models;

import java.util.List;

/**
 * Created by darrion on 2/28/17.
 */

public interface ReportManager {

    List<Report> _reports = null;

    public Report createReport();
    public List<Report> pastReports();
}
