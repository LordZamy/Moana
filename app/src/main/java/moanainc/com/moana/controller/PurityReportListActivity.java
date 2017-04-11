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
import moanainc.com.moana.model.Model;
import moanainc.com.moana.model.report.PurityReport;

/**
 * Created by josh baldwin on 3/17/2017.
 */

public class PurityReportListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purityreportlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listview = (ListView) findViewById(R.id.listview2);
        ArrayList<PurityReport> list = Model.getInstance().getCurrentUser().getReportManager().pastPurityReports();

        final PurityReportAdapter adapter = new PurityReportAdapter(this, list);
        listview.setAdapter(adapter);
    }

    private void goToWelcome() {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    public void onBackPressed(View view) {
        goToWelcome();
    }

    private class PurityReportAdapter extends ArrayAdapter<PurityReport> {

        final HashMap<String, Integer> purityReportMap = new HashMap<String, Integer>();

        public PurityReportAdapter(Context context, ArrayList<PurityReport> purityReports) {
            super(context, android.R.layout.simple_list_item_1, purityReports);
            for (int i = 0; i < purityReports.size(); ++i) {
                purityReportMap.put(purityReports.get(i).toString(), i);
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

            return purityReportMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }



}
