package moanainc.com.moana;

import android.util.Log;

import com.jjoe64.graphview.series.DataPoint;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import moanainc.com.moana.controller.HistoricalReportActivity;
import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.report.PurityCondition;
import moanainc.com.moana.model.report.PurityReport;
import moanainc.com.moana.model.user.User;

import org.junit.Assert;

/**
 * Created by lordzamy on 07/04/2017.
 */

public class PopulateGraphPointsTests {
    private ArrayList<Report> emptyReportList;
    private ArrayList<Report> nullReportList;
    private ArrayList<Report> randomReportList;

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

    @Test
    public void testPurityReportListRandom() {
        Random rand = new Random();

        // perform 100 iterations
        for (int testNum = 0; testNum < 100; testNum++) {
            randomReportList = new ArrayList<>(0);
            Report randomReport;
            for (int i = 0; i < 100; i++) {
                randomReport = new PurityReport("random", new Date(Math.abs(rand.nextLong())), new User(),
                        0.0, 0.0, PurityCondition.SAFE, rand.nextInt(500), 0);
                randomReportList.add(randomReport);
            }

            DataPoint[] actual = HistoricalReportActivity.populateGraphPoints(randomReportList);

            for (int i = 0; i < actual.length; i++) {
                Assert.assertTrue(actual[i].getY() - 0.001 <= 500);
                Assert.assertTrue(actual[i].getY() + 0.001 >= 0);

                Assert.assertTrue(actual[i].getX() <= 12);
                Assert.assertTrue(actual[i].getX() >= 1);
            }
        }
    }

    private void checkDataPointArraysEqual(DataPoint[] expected, DataPoint[] actual) {
        // length should always be 12
        Assert.assertEquals(actual.length, 12);

        // loop over all data points since library does not have equals()
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i].getX(), expected[i].getX(), 0.0001);
            Assert.assertEquals(expected[i].getY(), expected[i].getY(), 0.0001);
        }
    }
}
