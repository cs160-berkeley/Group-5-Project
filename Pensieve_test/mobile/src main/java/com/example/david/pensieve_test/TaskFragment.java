package com.example.david.pensieve_test;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by david on 4/17/16.
 */
public class TaskFragment extends Fragment{
    private final int DIALOG_ID = 0;
    private int hour;
    private int min;

    private static final String TASK_ID = "task_id";
    private static final String DIALOG_TIME = "DialogTime";

    private Tasks mTasks;
    private EditText mTitleField;
    //private EditText mTimeField;
    private Button mSetTimePickerDialog;
    private TextView mTimeField;

    public static TaskFragment newInstance(UUID taskId) {
        Bundle args = new Bundle();
        args.putSerializable(TASK_ID, taskId);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID taskId = (UUID) getArguments().getSerializable(TASK_ID);
        mTasks = TaskManager.get(getActivity()).getTask(taskId);
    }

    @Override
    public void onPause() {
        super.onPause();

        TaskManager.get(getActivity()).updateTask(mTasks);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_task, container, false);

        mTitleField = (EditText) v.findViewById(R.id.item_title);
        mTitleField.setText(mTasks.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTasks.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //mTimeField = (TextView) v.findViewById(R.id.time_text_view);

        mSetTimePickerDialog = (Button) v.findViewById(R.id.set_time_picker_dialog);
        mSetTimePickerDialog.setText("Set Time");
        mSetTimePickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //android.support.v4.app.FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = new TimePickerFragment();
                //dialog.show(manager, DIALOG_TIME);
                dialog.show(getChildFragmentManager(), DIALOG_TIME);
            }
        });


        return v;
    }

}
