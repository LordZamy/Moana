package moanainc.com.moana;

import org.junit.Before;
import org.junit.Test;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.report.PurityCondition;
import moanainc.com.moana.model.report.PurityReport;
import moanainc.com.moana.model.user.AccountType;
import moanainc.com.moana.model.user.User;
import moanainc.com.moana.model.user.Worker;

import static junit.framework.Assert.assertEquals;

/**
 * Created by josh baldwin on 4/5/2017.
 */

public class AddPurityReportTests {
    private ArrayList<Report> purityReports;

    @Before
    public void setup() {
        Report goodPurityReport;
        purityReports = new ArrayList<>();
        Date date = new Date(2017212);
        User creator = new User("dede", "dede");
        PurityCondition condition = PurityCondition.SAFE;
        goodPurityReport = new PurityReport("name", date, creator, 12, 12, condition, 12, 12);
        purityReports.add(goodPurityReport);
    }

    @Test
    public void testAddEmptyStringReport() {
        Report badPurityReport;
        try {
            badPurityReport = new PurityReport("", null, null, 0, 0, null, Integer.parseInt(""), Integer.parseInt(""));
            purityReports.add(badPurityReport);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException: Cannot parse an empty string.");
        }

        assertEquals(1, purityReports.size());

    }

    @Test
    public void testAddPurityReport() {
        assertEquals(1, purityReports.size());
    }

    @Test
    public void testAddEmptyFieldReport() {
        Date date = new Date(2014444);
        User creator = new User("Weeeee", "dede");
        PurityCondition condition = PurityCondition.UNSAFE;
        Report newReport = new PurityReport("", date, creator, 12, 12, condition, 12, 12);
        try {
            newReport.getName().charAt(1);
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println("Cannot leave name field blank.");
        }
    }
}
