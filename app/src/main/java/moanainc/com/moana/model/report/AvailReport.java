package moanainc.com.moana.model.report;

import java.util.Arrays;
import java.util.List;

import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.user.User;

/**
 * Created by darrion on 2/28/17.
 */

public class AvailReport implements Report {

    private String _reportName;
    private String _dateCreated;
    private User _creator;
    private String _status;
    private double _lat;
    private double _lng;
    public static List<String> legalStatus = Arrays.asList("Available", "Unavailable");


    public AvailReport(String reportName, String dateCreated, User creator, double lat, double lng, String status) {
        _reportName = reportName;
        _dateCreated = dateCreated;
        _creator = creator;
        _status = status;
        _lat = lat;
        _lng = lng;

    }

    public AvailReport(){

    }

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

    public void setName(String name) {
        _reportName = name;
    }

    public void setDate(String date){
        _dateCreated = date;
    }

    public void setCreator(User user) {
        _creator = user;
    }

    public void setLat(double lat) { _lat = lat; }

    public void setLng(double lng) { _lng = lng; }

    public Report createReport(){
        return new AvailReport();
    }



    @Override
    public String toString(){
        return _reportName + " | Status: " + _status + " | By: " + _creator.getAccount().getName();
    }
}
