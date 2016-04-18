package com.example.david.pensieve_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by david on 4/17/16.
 */
public class FamilyMemberFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.task_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitleTextView;
        private TextView mTimeTextview;
        private Tasks mTasks;

        public TaskHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_task);
            mTimeTextview = (TextView) itemView.findViewById(R.id.list_textClock);

        }

        public void bindTask(Tasks task) {
            mTasks = task;
            mTitleTextView.setText(task.getTitle());
            mTimeTextview.setText(task.getTime());
        }

        @Override
        public void onClick(View v) {
            Intent intent = TaskPagerActivity.newIntent(getActivity(), mTasks.getId());
            startActivity(intent);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Tasks> mTasksList;

        public TaskAdapter(List<Tasks> tasks){
            mTasksList = tasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item, parent, false);


            return new TaskHolder(view);
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
        inflater.inflate(R.menu.fragment_menu_family_member, menu);
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
            mAdapter = new TaskAdapter(tasks);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTasks(tasks);
            mAdapter.notifyDataSetChanged();
        }
    }
}
