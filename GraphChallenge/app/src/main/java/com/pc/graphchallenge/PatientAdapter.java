package com.pc.graphchallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;

/**
 * Created by Chase on 4/20/16.
 */
public class PatientAdapter extends BaseAdapter {


    EditText currentChart = null;
    Context context;
    private static LayoutInflater inflater=null;
    String[] result;
    EditText[] texts;
    public PatientAdapter(MainActivity mainActivity, String[] prgmNameList, EditText[] edits) {

        result=prgmNameList;
        context=mainActivity;
        texts=edits;
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
        EditText editText;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.patient_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.hello_world);
        holder.editText=(EditText) rowView.findViewById(R.id.notes);

        holder.tv.setText(result[position]);
        holder.editText.setVisibility(View.GONE);

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (currentChart != null) {
                    currentChart.setVisibility(View.GONE);
                }
                currentChart = (EditText) v.findViewById(R.id.notes);
                currentChart.setVisibility(View.VISIBLE);
            }
        });
        return rowView;
    }
}
