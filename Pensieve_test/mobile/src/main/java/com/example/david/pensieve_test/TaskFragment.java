package com.example.david.pensieve_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by david on 4/17/16.
 */
public class TaskFragment extends Fragment{
    private final int DIALOG_ID = 0;

    private static final String TASK_ID = "task_id";
    private static final String DIALOG_TIME = "DialogTime";

    private Tasks mTasks;
    private EditText mTitleField;
    private EditText mRemindTimeField;

    private Button mSetTimePickerDialog;
    private TextView mTimeField;
    private TextView mTimeAMPMField;
    //casey:
    private TextView mAddTaskOK;
    private TextView mAddTaskCancel;

    private TimePickerFragment dialog;
    private OnButtonClickListener listener;
    private int hour;
    private int minute;

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

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        dialog = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putInt("hour", hour);
        args.putInt("minute", minute);
        dialog.setArguments(args);
        dialog.setOnButtonClickListener(new TimePickerFragment.OnButtonClickListener() {
            @Override
            public void OnOKButtonClick() {
                mTimeField.setText(dialog.getTimetoString());
                mTimeAMPMField.setText(dialog.getAMPM());
            }

            @Override
            public void OnCancelButtonClick() {
            }
        });

        UUID taskId = (UUID) getArguments().getSerializable(TASK_ID);
        mTasks = TaskManager.get(getActivity()).getTask(taskId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_task_c, container, false);

        mTitleField = (EditText) v.findViewById(R.id.item_title);
        mRemindTimeField = (EditText) v.findViewById(R.id.pe_add_task_remind_time);

        mAddTaskOK = (TextView) v.findViewById(R.id.pe_add_task_ok);
        mAddTaskOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = mTitleField.getText().toString();
                mTasks.setTitle(s1.toString());
                String s2 = mRemindTimeField.getText().toString();
                mTasks.setRemindTime(s2.toString());

                int hourOfDay = dialog.getHour();
                mTasks.setTimeAMPM(hourOfDay > 11 ? "PM" : "AM");

                int minute = dialog.getMinute();
                int hour;
                if (hourOfDay > 11)
                    hour = hourOfDay - 12;
                else
                    hour = hourOfDay;

                String sTime = String.format("%02d:%02d", hour, minute);
                mTasks.setTime(sTime);

                TaskManager.get(getActivity()).updateTask(mTasks);
                if (listener != null)
                    listener.OnOKButtonClick();
            }
        }) ;

        mAddTaskCancel = (TextView) v.findViewById(R.id.pe_add_task_cancel);
        mAddTaskCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskDialogInfo.ok = false;
                if (listener != null)
                    listener.OnCancelButtonClick();
            }
        });


        mTitleField.setText(mTasks.getTitle());
        mTimeField = (TextView) v.findViewById(R.id.time_text_view);
        mTimeAMPMField = (TextView) v.findViewById(R.id.time_ampm_text_view);

        mTimeField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager manager = getFragmentManager();
                dialog.show(manager, DIALOG_TIME);
            }
        });
        return v;
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
    }

    public interface OnButtonClickListener {
        public void OnOKButtonClick();
        public void OnCancelButtonClick();
    }
}
