package com.example.david.pensieve_test;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private static final String ARG_TIME = "time";
    private static final String EXTRA_TIME = "com.example.david.pensieve_test.time";

    private TimePicker mTimePicker;

//    public static TimePickerFragment newInstance(Time time){
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_TIME, time);
//
//        TimePickerFragment fragment = new TimePickerFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Time time = (Time) getArguments().getSerializable(ARG_TIME);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);


        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time_picker, null);
        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_time_picker);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));


//        return new AlertDialog.Builder(getActivity())
//                .setView(v)
//                .setTitle("Set Time")
//                .setPositiveButton(android.R.string.ok, null)
////                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        int hour = mTimePicker.getHour();
////                        int minute = mTimePicker.getMinute();
////                        Time time = new Time(hour, minute);
////                        sendResult(Activity.RESULT_OK, time);
////                    }
////                })
//                .create();


    }

//    private void sendResult(int resultcode, Time time) {
//        if(getTargetFragment() == null) {
//            return;
//        }
//
//        Intent intent = new Intent();
//        intent.putExtra(EXTRA_TIME, time);
//        getTargetFragment().onActivityResult(getTargetRequestCode(), resultcode, intent);
//    }


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

        TextView textView = (TextView) getActivity().findViewById(R.id.time_text_view);
        textView.setText(textView.getText() + String.valueOf(current_hour) + ":" +
            String.valueOf(minute) + " " + am_pm);
    }
}
