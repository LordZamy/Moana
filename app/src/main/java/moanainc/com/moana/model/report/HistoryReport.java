package moanainc.com.moana.model.report;

import java.util.Date;

import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.user.User;

/**
 * Created by darrion on 2/28/17.
 */

public class HistoryReport implements Report {

    private String _reportName;
    private Date _dateCreated;
    private User _creator;
    private double _lat;
    private double _lng;


    public HistoryReport(String reportName, Date dateCreated, User creator, double lat, double lng){
        if(reportName == null) {
            _reportName = "No name given";
        } else {
            _reportName = reportName;
        }

        if(dateCreated == null) {
            _dateCreated = new Date();
        } else {
            _dateCreated = dateCreated;
        }

        if(creator == null) {
            _creator = new User();
        } else {
            _creator = creator;
        }



        if(lat > 90) {
            _lat = 90;
        } else if(lat < -90) {
            _lat = -90;
        } else {
            _lat = lat;
        }

        if(lng > 180) {
            _lng = 180;
        } else if(lat < -180) {
            _lng = -180;
        } else {
            _lng = lng;
        }

    }

    public HistoryReport(){}

    public String getName() {
        return _reportName;
    }

    public Date getDate(){
        return _dateCreated;
    }

    public User getCreator(){
        return _creator;
    }

    public double getLat() { return _lat; }

    public double getLng() { return _lng; }

    public Report createReport(){
        return new HistoryReport();
    }
}
