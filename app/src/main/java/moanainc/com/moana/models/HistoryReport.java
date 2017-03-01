package moanainc.com.moana.models;

/**
 * Created by darrion on 2/28/17.
 */

public class HistoryReport implements Report {

    private String _reportName;
    private String _dateCreated;
    private User _creator;


    public HistoryReport(String reportName, String dateCreated, User creator){
        _reportName = reportName;
        _dateCreated = dateCreated;
        _creator = creator;

    }

    public HistoryReport(){}

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
        return new HistoryReport();
    }
}
