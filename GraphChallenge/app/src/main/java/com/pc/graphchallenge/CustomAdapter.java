package com.pc.graphchallenge;

import android.view.View;

import android.content.Context;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;

import com.github.mikephil.charting.charts.BarChart;

/**
 * Created by Chase on 4/20/16.
 */
public class CustomAdapter extends BaseAdapter {


    String[] days = {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
    float[] time = {10f, 14f , 12f, 20f, 11f, 21f, 12f};
    BarChart currentChart = null;
    Context context;
    private static LayoutInflater inflater=null;
    String[] result;
    BarChart[] charts;
    public CustomAdapter(MainActivity mainActivity, String[] prgmNameList, BarChart[] prgmCharts) {


        result=prgmNameList;
        context=mainActivity;
        charts=prgmCharts;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        BarChart chart;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.graph_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.hello_world);
        holder.chart=(BarChart) rowView.findViewById(R.id.chart);
        ReminderChartOperator.create(holder.chart);
        ReminderChartOperator.setAxis(holder.chart, days, time);

        holder.tv.setText(result[position]);
        holder.chart.setVisibility(View.GONE);

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (currentChart != null) {
                    currentChart.setVisibility(View.GONE);
                }
                currentChart = (BarChart) v.findViewById(R.id.chart);
                currentChart.setVisibility(View.VISIBLE);
            }
        });
        return rowView;
    }
}
