package com.example.david.pensieve_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by david on 4/17/16.
 */
public class TaskFragment extends Fragment {
    private static final String TASK_ID = "task_id";

    private Tasks mTasks;
    private EditText mTitleField;
    private EditText mTimeField;

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

        mTimeField = (EditText) v.findViewById(R.id.item_time);
        mTimeField.setText(mTasks.getTime());
        mTimeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTasks.setTime(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return v;
    }


}
