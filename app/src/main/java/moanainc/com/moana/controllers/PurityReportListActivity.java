package moanainc.com.moana.controllers;

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
import moanainc.com.moana.models.Model;
import moanainc.com.moana.models.PurityReport;
import moanainc.com.moana.models.Report;

/**
 * Created by josh baldwin on 3/17/2017.
 */


public class PurityReportListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_purityreportlist);

        ListView listview = (ListView) findViewById(R.id.listview2);
        ArrayList<PurityReport> list = Model.getInstance().getCurrentUser().getReportManager().pastPurityReports();

        final PurityReportAdapter adapter = new PurityReportAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
    }

    public void goToWelcome(View view) {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    public void onBackPressed(View view) {
        goToWelcome(null);
    }

    private class PurityReportAdapter extends ArrayAdapter<PurityReport> {

        HashMap<String, Integer> purityReportMap = new HashMap<String, Integer>();

        public PurityReportAdapter(Context context, int textViewResourceId, ArrayList<PurityReport> purityReports) {
            super(context, textViewResourceId, purityReports);
            for (int i = 0; i < purityReports.size(); ++i) {
                purityReportMap.put(purityReports.get(i).toString(), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position).toString();
            return purityReportMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }



}
