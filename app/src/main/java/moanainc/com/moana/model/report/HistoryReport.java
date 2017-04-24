package moanainc.com.moana.model.report;

import java.util.Date;

import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.user.User;

/*
 * Created by darrion on 2/28/17.
 */

public class HistoryReport implements Report {

    private String _reportName;
    private Date _dateCreated;
    private User _creator;
    private double _lat;
    private double _lng;
    private PurityCondition _cond;
    private int _virusPPM;
    private int _contaminationPPM;


    public HistoryReport(String reportName, Date dateCreated, User creator, double lat, double lng, PurityCondition condition, int virusPPM, int contaminationPPM) {
        _reportName = reportName;
        _dateCreated = dateCreated;
        _creator = creator;
        _lat = lat;
        _lng = lng;
        _cond = condition;
        _virusPPM = virusPPM;
        _contaminationPPM = contaminationPPM;

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

    private HistoryReport(){}

    public String getName() {
        return _reportName;
    }

    public Date getDate(){
        return _dateCreated;
    }

    public User getCreator(){ return _creator; }

    public double getLat() { return _lat; }

    public double getLng() { return _lng; }

    public PurityCondition getCondition() { return _cond; }

    public int getVirusPPM() { return _virusPPM; }

    public int getContaminationPPM() { return _contaminationPPM; }

    public void setName(String reportName) {
        _reportName = reportName;
    }

    // created to preserve compatibility
    public void setDate(Date date) {
        _dateCreated = date;
    }

    public void setCreator(User user){ _creator = user; }

    public void setLat(double lat) { _lat = lat; }

    public void setLng(double lng) { _lng = lng; }

    public void setCondition(PurityCondition cond) { _cond = cond; }

    public void setVirusPPM(int ppm) { _virusPPM = ppm; }

    public void setContaminationPPM(int ppm) { _contaminationPPM = ppm; }

    public moanainc.com.moana.model.report.HistoryReport createReport(){
        return new moanainc.com.moana.model.report.HistoryReport();
    }

    @Override
    public String toString(){
        return _reportName + " | Date: " + _dateCreated + " | Location: " + _lat + ", " + _lng + "\nPurity Condition: " + _cond.toString()
                + " | Virus PPM: " + _virusPPM + " | Contamination PPM: " + _contaminationPPM + " | By: " + _creator.getAccount().getName();
    }
}
