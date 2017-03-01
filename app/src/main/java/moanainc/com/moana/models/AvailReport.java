package moanainc.com.moana.models;

/**
 * Created by darrion on 2/28/17.
 */

public class AvailReport implements Report {

    private String _reportName;
    private String _dateCreated;
    private User _creator;


    public AvailReport(String reportName, String dateCreated, User creator){
        _reportName = reportName;
        _dateCreated = dateCreated;
        _creator = creator;

    }

    public AvailReport(){}

    public String getName() {
        return _reportName;
    }

    public String getDate(){
        return _dateCreated;
    }

    public User getCreator(){
        return _creator;
    }

    public Report createReport(){
        return new AvailReport();
    }

    @Override
    public String toString(){
        return _reportName + " | Created on: " + _dateCreated + " | By: " + _creator.getAccount().getName();
    }
}
