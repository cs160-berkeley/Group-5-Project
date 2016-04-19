package com.example.david.pensieve_test;


import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import android.text.format.DateFormat;
import java.util.Calendar;


/**
 * Created by david on 4/18/16.
 */
public class TimePickerFragment extends DialogFragment{
    private String TAG = "@>@>@>";
    private static final String ARG_TIME = "time";
    private static final String EXTRA_TIME = "com.example.david.pensieve_test.time";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), mTimePickerListener, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

    }

    private TimePickerDialog.OnTimeSetListener mTimePickerListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            String am_pm = "AM";
            if (hourOfDay > 11) {
                am_pm = "PM";
            }

            int current_hour;
            if (hourOfDay > 11) {
                current_hour = hourOfDay - 12;
            } else {
                current_hour = hourOfDay;
            }

            String current_minute;
            if (minute < 10) {
                current_minute = "0" + minute;
            } else {
                current_minute = String.valueOf(minute);
            }


            TextView textView = (TextView) getActivity().findViewById(R.id.time_text_view);
            textView.setText(String.valueOf(current_hour) + ":" + current_minute + " " + am_pm);
            Log.d(TAG, textView.toString());

        }
    };

}
