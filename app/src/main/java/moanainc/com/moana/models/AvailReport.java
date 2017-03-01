package moanainc.com.moana.models;

import java.util.Arrays;
import java.util.List;

/**
 * Created by darrion on 2/28/17.
 */

public class AvailReport implements Report {

    private String _reportName;
    private String _dateCreated;
    private User _creator;
    private String _status;
    public static List<String> legalStatus = Arrays.asList("Available", "Unavailable");


    public AvailReport(String reportName, String dateCreated, User creator, String status) {
        _reportName = reportName;
        _dateCreated = dateCreated;
        _creator = creator;
        _status = status;

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
}
