package com.pc.graphchallenge;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.components.*;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;
import android.graphics.Color;

/**
 * Created by Chase on 4/19/16.
 * The Formatting for the charting program
 */
public class ReminderChartOperator {



    public static void create(BarChart chart) {
        /* set default colors and axis */
        chart.setTouchEnabled(true);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setHorizontalScrollBarEnabled(true);
        chart.setDescription("");
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        LimitLine ll = new LimitLine(12f, "");
        ll.setLineColor(Color.BLACK);
        YAxis yAxis = chart.getAxisLeft();
        ll.setLineWidth(2f);
        ll.setTextColor(Color.BLACK);
        ll.setTextSize(12f);
        yAxis.addLimitLine(ll);
        /* refresh */
        chart.invalidate();
    }

    public static void setAxis(BarChart chart, String[] days, float[] time) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < time.length; i++) {
            entries.add(new BarEntry(time[i], i));

        }
        /* set the axis data */
        BarDataSet dataSet = new BarDataSet(entries, null);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);


        YAxis leftAxis = chart.getAxisLeft();
        //leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5, false);
        leftAxis.setSpaceTop(20f);

        dataSet.setBarSpacePercent(1.0f);
        dataSet.setColor(Color.rgb(50, 205, 50));

        BarData data = new BarData(days, dataSet);
        data.setValueTextSize(12f);

        chart.setData(data);
        /* refresh */
        chart.invalidate();
    }
}
