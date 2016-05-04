package com.pc.graphchallenge;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.github.mikephil.charting.charts.BarChart;
import android.content.Context;
import android.widget.ListView;
import android.widget.EditText;



public class MainActivity extends Activity {

    ListView lv;
    Context context;
    Boolean isPatient = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        BarChart chart = null;
        EditText edits = null;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] prgNameList = {"Breakfast", "Dinner", "Feed Plant"};
        BarChart[] prgmCharts = {chart, chart, chart};
        EditText [] editTexts = {edits, edits, edits};

        setContentView(R.layout.activity_main);

        context=this;

        lv=(ListView) findViewById(R.id.graphListView);

        if (isPatient == true) {
            lv.setAdapter(new PatientAdapter(this, prgNameList, editTexts));
        } else {
            lv.setAdapter(new CustomAdapter(this, prgNameList,prgmCharts));
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
