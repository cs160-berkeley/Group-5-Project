package com.example.david.pensieve_test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Created by david on 4/17/16.
 */
public class FamilyMemberFragment extends Fragment {
    protected int role;

    private final String TAG = "@>@>@>";
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;
    private InputMethodManager mInputMethod;
    private View mView;
    private Handler mHandler = new Handler();
    private final static int green = Color.parseColor("#A5D6A7");
    private final static int red = Color.parseColor("#EF9A9A");
    private final static int pink = Color.parseColor("#FE6691");
    private UUID taskIdForNotification;

    public static FamilyMemberFragment newInstance(int role) {
        Bundle args = new Bundle();
        args.putInt("role", role);

        FamilyMemberFragment fragment = new FamilyMemberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.role = getArguments().getInt("role");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);

        mInputMethod = (InputMethodManager) getActivity().getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        /*
        if (this.role == 0) {
            mHandler.postDelayed(sendReminderToWatch, 3000); //5 sec
        }
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.task_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    /*
    *  Convenience method to add a specified number of minutes to a Date object
    *  From: http://stackoverflow.com/questions/9043981/how-to-add-minutes-to-my-date
    *  @param  minutes  The number of minutes to add
    *  @param  beforeTime  The time that will have minutes added to it
    *  @return  A date object with the specified number of minutes added to it
    */
    private Date addMinutesToDate(int minutes, Date beforeTime) {
        final long ONE_MINUTE_IN_MILLIS = 60000; // Millisecs

        long curTimeInMs = beforeTime.getTime();
        Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
        return afterAddingMins;
    }

    public static Date getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
        try {
            return sdf.parse(String.format("%d:%d", hour, minute));
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView mTitleTextView;
        private TextView mTimeAMPMTextview;
        private TextView mTimeTextview;
        private View mStatusBar;
        private LinearLayout mLinearLayoutwrapper;
        private LinearLayout mLinearLayoutwrapper1;
        private ArrayAdapter<CharSequence> mAdapter;
        private Spinner mSpinner;
        private EditText mNote;
        private Tasks mTasks;
        private ImageView mImageView;
        private int role;
        private TaskManager mTaskManager = TaskManager.get(getActivity());

        public TaskHolder(View itemView, int role) {
            super(itemView);
            this.role = role;
            final int currentRole = role;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_task);
            mTimeTextview = (TextView) itemView.findViewById(R.id.list_textClock);
            mTimeAMPMTextview = (TextView) itemView.findViewById(R.id.list_textClockAMPM);
            mStatusBar = (View) itemView.findViewById(R.id.list_status_bar);
            mLinearLayoutwrapper = (LinearLayout) itemView.findViewById(R.id.ll_chart_wrapper);
            mLinearLayoutwrapper1 = (LinearLayout) itemView.findViewById(R.id.ll_chart_wrapper1);
            mSpinner = (Spinner) itemView.findViewById(R.id.spinner);
            mAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.range_array, android.R.layout.simple_spinner_item);
            mSpinner.setAdapter(mAdapter);
            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    if (pos == 0) {
                        mImageView.setBackgroundResource(R.drawable.day_graph);
                    } else if (pos == 1) {
                        mImageView.setBackgroundResource(R.drawable.week_graph);
                    } else if (pos == 2) {
                        mImageView.setBackgroundResource(R.drawable.month_graph);
                    } else if (pos == 3) {
                        mImageView.setBackgroundResource(R.drawable.year_graph);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            // Save every keystroke to the database
            mNote = (EditText) itemView.findViewById(R.id.note);
            mNote.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (currentRole == 1) {
                        // Update the family member's note
                        Log.d(TAG, "Family member key pressed. Current text = " + mNote.getText().toString());
                        mTasks.setNote(mNote.getText().toString());
                    } else {
                        // Update the patient's note
                        Log.d(TAG, "Patient key pressed. Current text = " + mNote.getText().toString());
                        mTasks.setPatientNote(mNote.getText().toString());
                    }

                    mTaskManager.updateTask(mTasks);
                    return false;
                }
            });

            mImageView = (ImageView) itemView.findViewById(R.id.chart);
        }

        public void bindTask(Tasks task) {
            mTasks = task;

            if (role == 1) {
                // Show the family member note
                Log.d(TAG + " bindTask", "Family member editText set");
                mNote.setText(mTasks.getNote());
            } else {
                // Show the patient note
                Log.d(TAG + " bindTask", "Patient editText set");
                mNote.setText(mTasks.getPatientNote());
            }

            mTitleTextView.setText(task.getTitle());
            mTitleTextView.setTextColor(Color.BLACK);
            mTimeTextview.setText(task.getTime());
            mTimeTextview.setTextColor(Color.BLACK);
            mTimeAMPMTextview.setText(task.getTimeAMPM());
            mTimeAMPMTextview.setTextColor(Color.BLACK);

            String time = task.getTime();
            if (time != null && !time.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
                    Date currentTime = getCurrentTime();
                    String taskTimeAMPM = task.getTimeAMPM();
                    Date startTime = sdf.parse(time + " " + taskTimeAMPM);

                    int timeHour = Integer.parseInt(time.split(":")[0]);

                    if (("PM".equals(task.getTimeAMPM())) && (timeHour != 12)) {
                        startTime = addMinutesToDate(60 * 12, startTime);
                    }
                    Date endTime = addMinutesToDate(Integer.valueOf(task.getRemindTime()), startTime);

                    int taskStatus = task.isCompleted();
                    int startTimeDifference = currentTime.compareTo(startTime);
                    int endTimeDifference = currentTime.compareTo(endTime);


                    if (startTimeDifference < 0) {
                        mStatusBar.setBackgroundColor(Color.TRANSPARENT);
                    } else if (taskStatus == 1) {
                        mStatusBar.setBackgroundColor(FamilyMemberFragment.green);
                    } else {
                        mStatusBar.setBackgroundColor(FamilyMemberFragment.red);
                    }


                    if ((startTimeDifference >= 0) && (endTimeDifference < 0)) {
                        mTitleTextView.setText("› " + task.getTitle());
                        mTitleTextView.setTextColor(FamilyMemberFragment.pink);
                        mTimeTextview.setTextColor(FamilyMemberFragment.pink);
                        mTimeAMPMTextview.setTextColor(FamilyMemberFragment.pink);
                        mStatusBar.setBackgroundColor(Color.TRANSPARENT);
                    }

                } catch (final ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (this.role == 1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Actions");
                final CharSequence[] items = {
                        "Delete",
                        "Edit",
                        "Notify",
                        "Cancel"
                };

                builder.setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        TaskManager.get(getActivity()).deleteTask(mTasks);
                                        dialog.dismiss();
                                        updateUI();
                                        break;

                                    case 1:
                                        UUID firstTaskId = mTasks.getId();
                                        Intent intent = TaskPagerActivity.newIntent(getActivity(), firstTaskId);
                                        intent.putExtra("EDIT_MODE", true);
                                        startActivityForResult(intent, 1);
                                        break;

                                    case 2:
                                        taskIdForNotification = mTasks.getId();
                                        sendReminderToWatch.run();
                                        break;

                                    case 3:
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        }
                );
                builder.create().show();
            }
            return false;
        }

        @Override
        public void onClick(View v) {
            if (mView != null && mView != mLinearLayoutwrapper)
                mView.setVisibility(View.GONE);
            if (mLinearLayoutwrapper.getVisibility() == View.GONE) {
                mLinearLayoutwrapper.setVisibility(View.VISIBLE);
                mLinearLayoutwrapper1.setVisibility(role == 1 ? View.VISIBLE : View.GONE);
                mInputMethod.showSoftInput(mNote, InputMethodManager.SHOW_IMPLICIT);
                mNote.setOnEditorActionListener(
                        new EditText.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                        actionId == EditorInfo.IME_ACTION_DONE ||
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                                    mLinearLayoutwrapper.setVisibility(View.GONE);
                                    mInputMethod.hideSoftInputFromWindow(mNote.getWindowToken(), 0);
                                    return true;
                                }
                                return false;
                            }
                        });
                mView = mLinearLayoutwrapper;
            } else {
                mLinearLayoutwrapper.setVisibility(View.GONE);
            }
        }

        public View getStatusBar() {
            return mStatusBar;
        }

        public void setStatusBarColor(String color) {
            this.mStatusBar.setBackgroundColor(Color.parseColor(color));
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Tasks> mTasksList;
        private int role;

        public TaskAdapter(List<Tasks> tasks, int role) {
            mTasksList = tasks;
            this.role = role;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item, parent, false);

            return new TaskHolder(view, this.role);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {
            Tasks task = mTasksList.get(position);
            holder.bindTask(task);
        }

        @Override
        public int getItemCount() {
            return mTasksList.size();
        }

        public void setTasks(List<Tasks> tasks) {
            mTasksList = tasks;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (this.role == 1) {
            inflater.inflate(R.menu.fragment_menu_family_member, menu);
        } else {
            inflater.inflate(R.menu.fragment_menu_patient, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_task:
                Tasks task = new Tasks();
                Random rand = (new Random());
                int seed = ((rand.nextInt()) % 2);
                task.setCompleted(seed);
                TaskManager.get(getActivity()).addTask(task);

                // Adds task to lists
                Intent intent = TaskPagerActivity.newIntent(getActivity(), task.getId());
                startActivityForResult(intent, 1);
                return true;

            case R.id.menu_settings:
                Intent i = new Intent(getActivity(), Notification.class);
                startActivity(i);
                return true;

            case R.id.menu_logout:
                Intent j = new Intent(getActivity(), MainScreen.class);
                startActivity(j);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateUI() {
        TaskManager taskManager = TaskManager.get(getActivity());
        List<Tasks> tasks = taskManager.getTasksList();

        if (mAdapter == null) {
            mAdapter = new TaskAdapter(tasks, this.role);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTasks(tasks);
            mAdapter.notifyDataSetChanged();
        }
    }

    private Runnable sendReminderToWatch = new Runnable() {
        @Override
        public void run() {
            startWatch();
        }
    };

    private void startWatch(){
        String watchToData = "";
        TaskManager taskManager = TaskManager.get(getActivity());
        Tasks task = taskManager.getTask(taskIdForNotification);

        // List<Tasks> t = TaskManager.get(getActivity()).getTasksList();
        /*if (t.size() > 0) {
            Tasks task = t.get(0); // Sends 1st one

            watchToData += task.getTitle() + "@@@" + task.getTime() + "@@@" + task.getTimeAMPM() + "@@@" + task.getId();

            Intent sendIntent = new Intent(getActivity(), PhoneToWatchService.class);
            sendIntent.putExtra("dataToWatch", watchToData);
            getActivity().startService(sendIntent);
        } else {
            Log.d("START_WATCH", "There are no tasks to send a notification to the watch");
        }
        */
        watchToData += task.getTitle() + "@@@" + task.getTime() + "@@@" + task.getTimeAMPM() + "@@@" + task.getId();
        Intent sendIntent = new Intent(getActivity(), PhoneToWatchService.class);
        sendIntent.putExtra("dataToWatch", watchToData);
        getActivity().startService(sendIntent);
    }
}
