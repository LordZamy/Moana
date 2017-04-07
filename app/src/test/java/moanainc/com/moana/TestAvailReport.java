package moanainc.com.moana;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import moanainc.com.moana.model.report.AvailReport;
import moanainc.com.moana.model.user.User;
import static junit.framework.Assert.*;
/**
 * Created by darrion on 4/7/17.
 */

public class TestAvailReport {

    AvailReport availReport;
    String _reportName;
    Date _dateCreated;
    User _creator;
    String _status;
    double _lat;
    double _lng;


    @Before public void setup() {
        _reportName = "Dummy report";
        _dateCreated = new Date();
        _creator = new User();
        _status = "Available";
        _lat = 0;
        _lng = 0;
        availReport = new AvailReport(_reportName, _dateCreated, _creator, _lat, _lng, _status);
    }

    // Name should have empty string, not null value.
    @Test public void testNullNameAvailabilityReport() {
        assertNotNull(availReport.getName());
    }

    // Date should be applied, even if null value is passed.
    @Test public void testNullDateCreatedAvailabilityReport() {
        assertNotNull(availReport.getDate());
    }

    // User should not be null, should be filled with default values.
    @Test public void testNullUserAvailabilityReport() {
        assertNotNull(availReport.getCreator());
    }

    // Status should not be null, should be set to a default value.
    @Test public void testNullStatusAvailabilityReport() {
        assertNotNull(availReport.getStatus());
    }
}
