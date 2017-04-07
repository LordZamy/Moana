package moanainc.com.moana;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Date;

import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.report.PurityCondition;
import moanainc.com.moana.model.report.PurityReport;
import moanainc.com.moana.model.user.User;
import moanainc.com.moana.firebase.FirebaseInterface;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Reece on 4/7/2017.
 */

public class UserCreateReportTest {

    User user;



    @Before
    public void setup() {
        user = new User("user","password");
    }

    @Test
    public void testCreateAvailabilityReport() {
        user.createAvailReport("name", new Date(), 1,1,"Available");
        assertEquals(1, FirebaseInterface.getAvailabilityReports().size());
    }
}
