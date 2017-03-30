package moanainc.com.moana.model.report;

import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.user.User;

/**
 * Created by darrion on 2/28/17.
 */

public class SourceReport implements Report {

    private String _reportName;
    private String _dateCreated;
    private User _creator;
    private double _lat;
    private double _lng;


    public SourceReport(String reportName, String dateCreated, User creator, double lat, double lng){
        _reportName = reportName;
        _dateCreated = dateCreated;
        _creator = creator;
        _lat = lat;
        _lng = lng;

    }

    public SourceReport(){}

    public String getName() {
        return _reportName;
    }

    public String getDate(){
        return _dateCreated;
    }

    public User getCreator(){
        return _creator;
    }

    public double getLat() { return _lat; }

    public double getLng() { return _lng; }

    public Report createReport(){
        return new SourceReport();
    }
}
