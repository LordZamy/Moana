package moanainc.com.moana.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import moanainc.com.moana.R;
import moanainc.com.moana.firebase.FirebaseInterface;
import moanainc.com.moana.model.Report;

/**
 * Created by reecedantin on 2/28/17.
 */

public class ReportListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listview = (ListView) findViewById(R.id.listview);
        //ArrayList<Report> list = Model.getInstance().getCurrentUser().getReportManager().pastReports();
        ArrayList<Report> list;

        switch(getIntent().getIntExtra("filter", 0)) {
            case 0:
                list = FirebaseInterface.getAvailabilityReports();
                break;
            case 1:
                list = FirebaseInterface.getPurityReports();
                break;
            case 2:
                list = FirebaseInterface.getSourceReports();
                break;
            case 3:
                list = FirebaseInterface.getHistoryReports();
                break;
            case 4:
                list = FirebaseInterface.getAllReports();
                break;
            default:
                list = FirebaseInterface.getAvailabilityReports();
                break;
        }

        Collections.sort(list, new Comparator<Report>() {
            @Override
            public int compare(Report r1, Report r2)
            {
                return  r1.getDate().compareTo(r2.getDate());
            }
        });

        final ReportAdapter adapter = new ReportAdapter(this, list);
        listview.setAdapter(adapter);
    }

    private void goToWelcome() {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    public void onBackPressed(View view) {
        goToWelcome();
    }

    private class ReportAdapter extends ArrayAdapter<Report> {

        final HashMap<String, Integer> reportMap = new HashMap<String, Integer>();

        public ReportAdapter(Context context, ArrayList<Report> reports) {
            super(context, android.R.layout.simple_list_item_1, reports);
            for (int i = 0; i < reports.size(); ++i) {
                reportMap.put(reports.get(i).toString(), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item;

            try {
                item = getItem(position).toString();
            } catch (Exception err) {
                item = null;
            }

            return reportMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }



}
