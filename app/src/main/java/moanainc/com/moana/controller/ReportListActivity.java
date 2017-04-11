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
        ArrayList<Report> list = FirebaseInterface.getAvailabilityReports();

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

        final ReportAdapter adapter = new ReportAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
    }

    public void goToWelcome(View view) {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    public void onBackPressed(View view) {
        goToWelcome(null);
    }

    private class ReportAdapter extends ArrayAdapter<Report> {

        HashMap<String, Integer> reportMap = new HashMap<String, Integer>();

        public ReportAdapter(Context context, int textViewResourceId, ArrayList<Report> reports) {
            super(context, textViewResourceId, reports);
            for (int i = 0; i < reports.size(); ++i) {
                reportMap.put(reports.get(i).toString(), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position).toString();
            return reportMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }



}
