package moanainc.com.moana.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import moanainc.com.moana.R;
import moanainc.com.moana.firebase.FirebaseInterface;
import moanainc.com.moana.model.Model;
import moanainc.com.moana.model.Report;
import moanainc.com.moana.model.report.PurityReport;

/*
 * Created by josh baldwin on 3/29/2017.
 */

public class HistoricalReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Report> purityReportList = FirebaseInterface.getPurityReports();

        // sums for each month
        float[] PPMSums = new float[12];
        int[] numSums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //new int[12];

        // compute sum and number of sums
        for (Report report : purityReportList)  {
            Date reportDate = report.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(reportDate);
            int month = cal.get(Calendar.MONTH);

            PPMSums[month] += ((PurityReport) report).getVirusPPM();
            numSums[month]++;
        }

        DataPoint[] graphPoints = new DataPoint[12];

        // compute and assign averages
        for (int i = 0; i < 12; i++) {
            if (numSums[i] == 0)
                graphPoints[i] = new DataPoint(i + 1, 0);
            else
                graphPoints[i] = new DataPoint(i + 1, PPMSums[i] / numSums[i]);
        }

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(graphPoints);


        series.setTitle("Month vs. PPM");
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);

        graph.addSeries(series);
        

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(13);

        graph.getViewport().setScrollable(true);
        graph.getViewport().setScrollableY(true);
    }


}
