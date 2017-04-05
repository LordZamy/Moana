package moanainc.com.moana;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Date;

import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.report.PurityCondition;
import moanainc.com.moana.model.report.PurityReport;
import moanainc.com.moana.model.user.User;

import static junit.framework.Assert.assertEquals;

/**
 * Created by USER on 4/5/2017.
 */

public class AddPurityReportTests {
    private Report goodPurityReport;
    private Report badPurityReport;
    private ArrayList<Report> purityReports;

    @Before
    public void setup() {
        purityReports = new ArrayList<>();
        Date date = new Date(2017212);
        User creator = new User("dede", "dede");
        PurityCondition condition = PurityCondition.SAFE;
        goodPurityReport = new PurityReport("name", date, creator, 12, 12, condition, 12, 12);

        //badPurityReport = new PurityReport(null, null, null, 0, 0, null, 0, 0);
        purityReports.add(goodPurityReport);
    }

    @Test
    public void testAddNullReport() {
        try {
            badPurityReport = new PurityReport(null, null, null, 0, 0, null, 0, 0);
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
        }
        purityReports.add(badPurityReport);
        assertEquals(2, purityReports.size());

    }

    @Test
    public void testAddPurityReport() {
        assertEquals(1, purityReports.size());
    }
}
