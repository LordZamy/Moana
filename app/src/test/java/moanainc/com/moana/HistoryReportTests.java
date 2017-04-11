package moanainc.com.moana;

/**
 * Created by Micah Terrell on 4/7/2017.
 */

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.report.HistoryReport;

public class HistoryReportTests {
    private Report historyReportNullObjects;
    private Report historyReportInvalidLatLong;
    private Report historyReport3;
    private Report historyReport4;

    @Test
    public void TestHistoryReportNullChecking() {
        //Make sure the constructor set default values
        Report historyReportNullObjects = new HistoryReport(0, 0);

        Assert.assertNotNull(historyReportNullObjects.getName());
        Assert.assertNotNull(historyReportNullObjects.getDate());
        Assert.assertNotNull(historyReportNullObjects.getCreator());
    }

    @Test
    public void TestHistoryReportLatLongBounds() {
        //Test the lat and long to make sure they are in bounds

        Report historyReportInvalidLatLong = new HistoryReport(-10000, 100000);

        double lat = historyReportInvalidLatLong.getLat();
        double lng = historyReportInvalidLatLong.getLng();

        Assert.assertTrue(lat >= -90 && lat <= 90);
        Assert.assertTrue(lng >= -180 && lng <= 180);
    }
}
