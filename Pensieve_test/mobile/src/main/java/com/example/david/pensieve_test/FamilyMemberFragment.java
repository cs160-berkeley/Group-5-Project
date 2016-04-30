package com.example.david.pensieve_test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

/**
 * Created by david on 4/17/16.
 */
public class FamilyMemberFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;
    private InputMethodManager mInputMethod;
    private View mView;
    protected int role;


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
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.task_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView mTitleTextView;
        private TextView mTimeAMPMTextview;
        private TextView mTimeTextview;
        private LinearLayout mLinearLayoutwrapper;
        private LinearLayout mLinearLayoutwrapper1;
        private ArrayAdapter<CharSequence> mAdapter;
        private Spinner mSpinner;
        private EditText mNote;
        private Tasks mTasks;
        private ImageView mImageView;
        private int role;

        public TaskHolder(View itemView, int role) {
            super(itemView);
            this.role = role;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_task);
            mTimeTextview = (TextView) itemView.findViewById(R.id.list_textClock);
            mTimeAMPMTextview = (TextView) itemView.findViewById(R.id.list_textClockAMPM);
            mLinearLayoutwrapper = (LinearLayout) itemView.findViewById(R.id.ll_chart_wrapper);
            mLinearLayoutwrapper1 = (LinearLayout) itemView.findViewById(R.id.ll_chart_wrapper1);
            mSpinner = (Spinner) itemView.findViewById(R.id.spinner);
            mAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.range_array, android.R.layout.simple_spinner_item);
            mSpinner.setAdapter(mAdapter);
            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    if (pos == 0)
                        mImageView.setBackgroundResource(R.drawable.day_graph);
                    else if (pos == 1)
                        mImageView.setBackgroundResource(R.drawable.week_graph);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            mNote = (EditText) itemView.findViewById(R.id.note);
            mImageView = (ImageView) itemView.findViewById(R.id.chart);
        }

        public void bindTask(Tasks task) {
            mTasks = task;
            mTitleTextView.setText(task.getTitle());
            mTimeTextview.setText(task.getTime());
            mTimeAMPMTextview.setText(task.getTimeAMPM());
        }

        @Override
        public boolean onLongClick(View v) {
            if (this.role == 1) {
                new AlertDialog.Builder(getActivity())
                        //set message, title, and icon
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                TaskManager.get(getActivity()).deleteTask(mTasks);
                                dialog.dismiss();
                            }
                        })

                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
            return false;
        }

        @Override
        public void onClick(View v) {
//                Intent intent = TaskPagerActivity.newIntent(getActivity(), mTasks.getId());
//                startActivity(intent);
            if (mView != null && mView != mLinearLayoutwrapper)
                mView.setVisibility(View.GONE);
            if (mLinearLayoutwrapper.getVisibility() == View.GONE) {
                mLinearLayoutwrapper.setVisibility(View.VISIBLE);
                mLinearLayoutwrapper1.setVisibility(role == 1 ? View.VISIBLE : View.GONE);
                mNote.requestFocus();
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
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_task:
                Tasks task = new Tasks();
                TaskManager.get(getActivity()).addTask(task);
                Intent intent = TaskPagerActivity.newIntent(getActivity(), task.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
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

//    private void setUpItemTouchHelper() {
//        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//
//            }
//        }
//    }
}
