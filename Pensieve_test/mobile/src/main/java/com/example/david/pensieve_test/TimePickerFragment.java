package com.example.david.pensieve_test;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.widget.TimePicker;

import android.text.format.DateFormat;

import java.util.Calendar;


/**
 * Created by david on 4/18/16.
 */
public class TimePickerFragment extends DialogFragment {
    private String TAG = "@>@>@>";
    private static final String ARG_TIME = "time";
    private static final String EXTRA_TIME = "com.example.david.pensieve_test.time";
    public String time_str;

    private OnButtonClickListener listener;
    private int hourOfDay = -1;
    private int minute = -1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (hourOfDay == -1 && minute == -1) {
            hourOfDay = getArguments().getInt("hour");
            minute = getArguments().getInt("minute");
        }

        return new TimePickerDialog(getActivity(), mTimePickerListener, hourOfDay, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    private TimePickerDialog.OnTimeSetListener mTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            TimePickerFragment.this.hourOfDay = hourOfDay;
            TimePickerFragment.this.minute = minute;
            if (listener != null)
                listener.OnOKButtonClick();
        }
    };

    public int getHour() {
        if (hourOfDay == -1)
            return 12;
        else
            return hourOfDay;
    }

    public int getMinute() {
        if (minute == -1)
            return 0;
        else return minute;
    }

    public String getTimetoString() {
        int current_hour;
        if (hourOfDay > 12) {
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
        return String.valueOf(current_hour) + ":" + current_minute;
    }

    public String getAMPM() {
        return (hourOfDay > 11 || hourOfDay == -1) ? "PM" : "AM";
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
    }

    public interface OnButtonClickListener {
        public void OnOKButtonClick();
        public void OnCancelButtonClick();
    }
}
