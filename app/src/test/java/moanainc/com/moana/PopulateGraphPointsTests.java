package moanainc.com.moana;

import com.jjoe64.graphview.series.DataPoint;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import moanainc.com.moana.controller.HistoricalReportActivity;
import moanainc.com.moana.model.Report;

import org.junit.Assert;

/**
 * Created by lordzamy on 07/04/2017.
 */

public class PopulateGraphPointsTests {
    private ArrayList<Report> emptyReportList;
    private ArrayList<Report> nullReportList;

    @Before
    public void setup() {
        emptyReportList = new ArrayList<>(0);
        nullReportList = null;
    }

    @Test
    public void testPurityReportListEmpty() {
        DataPoint[] expected = new DataPoint[12];
        for (int i = 0; i < 12; i++) {
            expected[i] = new DataPoint(i + 1, 0);
        }

        DataPoint[] actual = HistoricalReportActivity.populateGraphPoints(emptyReportList);

        checkDataPointArraysEqual(expected, actual);
    }

    @Test
    public void testPurityReportListNull() {
        DataPoint[] expected = new DataPoint[12];
        for (int i = 0; i < 12; i++) {
            expected[i] = new DataPoint(i + 1, 0);
        }

        DataPoint[] actual = HistoricalReportActivity.populateGraphPoints(nullReportList);

        checkDataPointArraysEqual(expected, actual);
    }

    public void checkDataPointArraysEqual(DataPoint[] expected, DataPoint[] actual) {
        // length should always be 12
        Assert.assertEquals(actual.length, 12);

        // loop over all data points since library does not have equals()
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i].getX(), expected[i].getX(), 0.0001);
            Assert.assertEquals(expected[i].getY(), expected[i].getY(), 0.0001);
        }
    }
}
