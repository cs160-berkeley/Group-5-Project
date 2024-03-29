package com.example.david.pensieve_test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by david on 4/17/16.
 */
public class TaskFragment extends Fragment {
    private final int DIALOG_ID = 0;

    private String TAG = "@>@>@>";
    private static final String TASK_ID = "task_id";
    private static final String DIALOG_TIME = "DialogTime";

    private Tasks mTasks;
    private EditText mTitleField;
    private EditText mRemindTimeField;

    private TextView mTimeField;
    private TextView mTimeAMPMField;

    private TextView mAddTaskOK;
    private TextView mAddTaskCancel;
    boolean editMode;

    private ImageView mSundayButton;
    private boolean isSundayCheck = false;
    private ImageView mMondayButton;
    private boolean isMondayCheck = false;
    private ImageView mTuesdayButton;
    private boolean isTuesdayCheck = false;
    private ImageView mWednesdayButton;
    private boolean isWednesdayCheck = false;
    private ImageView mThursdayButton;
    private boolean isThursdayCheck = false;
    private ImageView mFridayButton;
    private boolean isFridayCheck = false;
    private ImageView mSaturdayButton;
    private boolean isSaturdayCheck = false;

    private TextView mReminderReviewField;

    private TimePickerFragment dialog;
    private OnButtonClickListener listener;
    private int hour;
    private int minute;
    boolean timePickerUsed = false;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        editMode = false;

        // Check to see if we are in edit mode
        if (savedInstanceState == null) {
            Bundle extras = getActivity().getIntent().getExtras();
            if(extras == null) {
                editMode = false;
            } else {
                editMode = extras.getBoolean("EDIT_MODE");
            }
        } else {
            editMode = (Boolean) savedInstanceState.getSerializable("EDIT_MODE");
        }
        Log.d(TAG, "Edit Mode = " + editMode);

        Calendar calendar = Calendar.getInstance();
        if (editMode) {
            String[] nums = mTasks.getTime().split(":");
            hour = Integer.parseInt(nums[0]);
            minute = Integer.parseInt(nums[1]);
        } else {
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
        }

        dialog = new TimePickerFragment();

        Bundle args = new Bundle();
        args.putInt("hour", hour);
        args.putInt("minute", minute);
        dialog.setArguments(args);

        dialog.setOnButtonClickListener(new TimePickerFragment.OnButtonClickListener() {
            @Override
            public void OnOKButtonClick() {
                timePickerUsed = true;
                mTimeField.setText(dialog.getTimetoString());
                mTimeAMPMField.setText(dialog.getAMPM());
                updateRemindReview();
            }

            @Override
            public void OnCancelButtonClick() {
            }
        });

        View v = inflater.inflate(R.layout.new_task_c, container, false);

        mTitleField = (EditText) v.findViewById(R.id.item_title);
        if (mTasks.getTitle() != null) {
            mTitleField.setText(mTasks.getTitle());
        }

        mRemindTimeField = (EditText) v.findViewById(R.id.pe_add_task_remind_time);
        if (mTasks.getRemindTime() != null) {
            mRemindTimeField.setText(mTasks.getRemindTime());
        }

        mReminderReviewField = (TextView) v.findViewById(R.id.pe_add_task_remind_time_review);


        mSundayButton = (ImageView) v.findViewById(R.id.imageButtonSunday);
        if (mTasks.isRepeatSunday()) {
            mSundayButton.setImageResource(R.drawable.p_sunday);
            isSundayCheck = true;
        }
        mSundayButton.setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View v) {
                                                 if (isSundayCheck)
                                                     mSundayButton.setImageResource(R.drawable.sunday);
                                                 else
                                                     mSundayButton.setImageResource(R.drawable.p_sunday);
                                                 isSundayCheck = !isSundayCheck;
                                                 mTasks.setRepeatSunday(isSundayCheck);
                                                 updateRemindReview();

                                             }
                                         }
        );
        mMondayButton = (ImageView) v.findViewById(R.id.imageButtonMonday);
        if (mTasks.isRepeatMonday()) {
            mMondayButton.setImageResource(R.drawable.p_monday);
            isMondayCheck = true;
        }
        mMondayButton.setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View v) {
                                                 if (isMondayCheck)
                                                     mMondayButton.setImageResource(R.drawable.monday);
                                                 else
                                                     mMondayButton.setImageResource(R.drawable.p_monday);
                                                 isMondayCheck = !isMondayCheck;
                                                 mTasks.setRepeatMonday(isMondayCheck);
                                                 updateRemindReview();

                                             }
                                         }
        );
        mTuesdayButton = (ImageView) v.findViewById(R.id.imageButtonTuesday);
        if (mTasks.isRepeatTuesday()) {
            mTuesdayButton.setImageResource(R.drawable.p_tuesday);
            isTuesdayCheck = true;
        }
        mTuesdayButton.setOnClickListener(new View.OnClickListener() {
                                              public void onClick(View v) {
                                                  if (isTuesdayCheck)
                                                      mTuesdayButton.setImageResource(R.drawable.tuesday);
                                                  else
                                                      mTuesdayButton.setImageResource(R.drawable.p_tuesday);
                                                  isTuesdayCheck = !isTuesdayCheck;
                                                  mTasks.setRepeatTuesday(isTuesdayCheck);
                                                  updateRemindReview();

                                              }
                                          }
        );
        mWednesdayButton = (ImageView) v.findViewById(R.id.imageButtonWednesday);
        if (mTasks.isRepeatWednesday()) {
            mWednesdayButton.setImageResource(R.drawable.p_wednesday);
            isWednesdayCheck = true;
        }
        mWednesdayButton.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    if (isWednesdayCheck)
                                                        mWednesdayButton.setImageResource(R.drawable.wednesday);
                                                    else
                                                        mWednesdayButton.setImageResource(R.drawable.p_wednesday);
                                                    isWednesdayCheck = !isWednesdayCheck;
                                                    mTasks.setRepeatWednesday(isWednesdayCheck);
                                                    updateRemindReview();

                                                }
                                            }
        );
        mThursdayButton = (ImageView) v.findViewById(R.id.imageButtonThurday);
        if (mTasks.isRepeatThursday()) {
            mThursdayButton.setImageResource(R.drawable.p_thursday);
            isThursdayCheck = true;
        }
        mThursdayButton.setOnClickListener(new View.OnClickListener() {
                                              public void onClick(View v) {
                                                  if (isThursdayCheck)
                                                      mThursdayButton.setImageResource(R.drawable.thursday);
                                                  else
                                                      mThursdayButton.setImageResource(R.drawable.p_thursday);
                                                  isThursdayCheck = !isThursdayCheck;
                                                  mTasks.setRepeatThursday(isThursdayCheck);
                                                  updateRemindReview();

                                              }
                                          }
        );
        mFridayButton = (ImageView) v.findViewById(R.id.imageButtonFriday);
        if (mTasks.isRepeatFriday()) {
            mFridayButton.setImageResource(R.drawable.p_friday);
            isFridayCheck = true;
        }
        mFridayButton.setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View v) {
                                                 if (isFridayCheck)
                                                     mFridayButton.setImageResource(R.drawable.friday);
                                                 else
                                                     mFridayButton.setImageResource(R.drawable.p_friday);
                                                 isFridayCheck = !isFridayCheck;
                                                 mTasks.setRepeatFriday(isFridayCheck);
                                                 updateRemindReview();

                                             }
                                         }
        );
        mSaturdayButton = (ImageView) v.findViewById(R.id.imageButtonSaturday);
        if (mTasks.isRepeatSaturday()) {
            mSaturdayButton.setImageResource(R.drawable.p_saturday);
            isSaturdayCheck = true;
        }
        mSaturdayButton.setOnClickListener(new View.OnClickListener() {
                                               public void onClick(View v) {
                                                   if (isSaturdayCheck)
                                                       mSaturdayButton.setImageResource(R.drawable.saturday);
                                                   else
                                                       mSaturdayButton.setImageResource(R.drawable.p_saturday);
                                                   isSaturdayCheck = !isSaturdayCheck;
                                                   mTasks.setRepeatSaturday(isSaturdayCheck);
                                                   updateRemindReview();

                                               }
                                           }
        );
        mAddTaskOK = (TextView) v.findViewById(R.id.pe_add_task_ok);
        mAddTaskOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = mTitleField.getText().toString();
                mTasks.setTitle(s1.toString());
                String s2 = mRemindTimeField.getText().toString();
                mTasks.setRemindTime(s2.toString());

                if (timePickerUsed) {
                    int hourOfDay = dialog.getHour();
                    int minute = dialog.getMinute();
                    //int hourOfDay = Integer.parseInt(mTasks.getTime().substring(0, 2));
                    //int minute = Integer.parseInt(mTasks.getTime().substring(3));

                    mTasks.setTimeAMPM(hourOfDay > 11 ? "PM" : "AM");

                    if (hourOfDay == 0) {
                        hourOfDay = 12;
                    } else if (hourOfDay >= 13) {
                        hourOfDay -= 12;
                    }

                    String sTime = String.format("%d:%02d", hourOfDay, minute);
                    Log.d("OK_BUTTON_CLICKED", "New time = " + sTime);
                    mTasks.setTime(sTime);
                }


                updateRemindReview();

                TaskManager.get(getActivity()).updateTask(mTasks);
                if (listener != null) {
                    listener.OnOKButtonClick();
                }
//                Tasks task = new Tasks();
//                TaskManager.get(getActivity()).addTask(task);
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
        if (mTasks.getTime() != null) {
            mTimeField.setText(mTasks.getTime());
        }

        mTimeField.setPaintFlags(mTimeField.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mTimeAMPMField = (TextView) v.findViewById(R.id.time_ampm_text_view);
        if (mTasks.getTimeAMPM() != null) {
            mTimeAMPMField.setText(mTasks.getTimeAMPM());
        }

        mTimeField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager manager = getFragmentManager();
                dialog.show(manager, DIALOG_TIME);
            }
        });


        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateRemindReview();
            }

            @Override
            // http://stackoverflow.com/questions/6070805/prevent-enter-key-on-edittext-but-still-show-the-text-as-multi-line
            // Escape newlines when entering task title
            public void afterTextChanged(Editable s) {
                for (int i = s.length(); i > 0; i--) {
                    if (s.subSequence(i-1, i).toString().equals("\n")) {
                        s.replace(i-1, i, "");
                        EditText length = (EditText) getView().findViewById(R.id.pe_add_task_remind_time);
                        length.requestFocus();
                    }
                }
                String textString = s.toString();
            }
        });
        updateRemindReview();
        return v;
    }

    public void updateRemindReview() {

        boolean isRepeat = isFridayCheck | isMondayCheck | isSaturdayCheck | isSundayCheck
                | isThursdayCheck | isTuesdayCheck | isWednesdayCheck;
        boolean isRepeatAll = isFridayCheck & isMondayCheck & isSaturdayCheck & isSundayCheck
                & isThursdayCheck & isTuesdayCheck & isWednesdayCheck;

        String sr0 = (isRepeat ? " every":"") + (isSundayCheck ? " Sunday" :"") +
                (isMondayCheck ? " Monday" :"") + (isTuesdayCheck ? " Tuesday" :"") +
                (isWednesdayCheck ? " Wednesday" :"") + (isThursdayCheck ? " Thursday" :"") +
                (isFridayCheck ? " Friday" :"") + (isSaturdayCheck ? " Saturday" :"") +
                 ".";


        String s1 = mTitleField.getText().toString();
        String s2 = mTimeField.getText().toString();
        String s3 = (mTimeAMPMField.getText().toString().toLowerCase());
        String sr1;

        String s5 = s1+" at "+s2+s3;
        if( isRepeatAll) {
            sr1 = s5+ " everyday.";
        } else {
            sr1= s5+sr0;

        }

        mReminderReviewField.setText(sr1);

    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {


        this.listener = listener;
    }

    public interface OnButtonClickListener {
        public void OnOKButtonClick();
        public void OnCancelButtonClick();
    }
}
