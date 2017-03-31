package moanainc.com.moana.model.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.user.User;

/*
 * Created by darrion on 2/28/17.
 */

public class PurityReport implements Report {

    private String _reportName;
    private Date _dateCreated;
    private User _creator;
    private double _lat;
    private double _lng;
    private PurityCondition _cond;
    private int _virusPPM;
    private int _contaminationPPM;


    public PurityReport(String reportName, Date dateCreated, User creator, double lat, double lng, PurityCondition condition, int virusPPM, int contaminationPPM) {
        _reportName = reportName;
        _dateCreated = dateCreated;
        _creator = creator;
        _lat = lat;
        _lng = lng;
        _cond = condition;
        _virusPPM = virusPPM;
        _contaminationPPM = contaminationPPM;

    }

    private PurityReport(){}

    public String getName() {
        return _reportName;
    }

    public String getDate(){
        return _dateCreated.toString();
    }

    // created to preserve compatibility
    public Date getDateActual() {
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

    public void setDateCreated(String date){
        try{
            _dateCreated = (new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)).parse(date);
        } catch (ParseException perror) {
            _dateCreated = null;
        }
    }

    // created to preserve compatibility
    public void setDateCreated(Date date) {
        _dateCreated = date;
    }

    public void setCreator(User user){ _creator = user; }

    public void setLat(double lat) { _lat = lat; }

    public void setLng(double lng) { _lng = lng; }

    public void setCondition(PurityCondition cond) { _cond = cond; }

    public void setVirusPPM(int ppm) { _virusPPM = ppm; }

    public void setContaminationPPM(int ppm) { _contaminationPPM = ppm; }

    public PurityReport createReport(){
        return new PurityReport();
    }

    @Override
    public String toString(){
        return _reportName + " | Date: " + _dateCreated + " | Location: " + _lat + ", " + _lng + "\nPurity Condition: " + _cond.toString()
        + " | Virus PPM: " + _virusPPM + " | Contamination PPM: " + _contaminationPPM + " | By: " + _creator.getAccount().getName();
    }
}
