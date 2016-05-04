package com.example.david.pensieve_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;
import java.util.UUID;

/**
 * Created by david on 4/17/16.
 */
public class TaskPagerActivity extends AppCompatActivity {
    private final String TAG = "@>@>@>";
    public static final String EXTRA_TASK_ID = "com.example.david.pensieve_test.task_id";

    private ViewPager mViewPager;
    private List<Tasks> mTasks;

    public static Intent newIntent(Context packageContext, UUID taskId) {
        Intent intent = new Intent(packageContext, TaskPagerActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_pager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        mViewPager = (ViewPager) findViewById(R.id.activity_task_pager_view_pager);

        mTasks = TaskManager.get(this).getTasksList();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                final Tasks t = mTasks.get(position);
                TaskFragment taskFragment = TaskFragment.newInstance(t.getId());
                taskFragment.setOnButtonClickListener(new TaskFragment.OnButtonClickListener() {
                    @Override
                    public void OnOKButtonClick() {
                        finish();
                        Log.d(TAG, "ok clicked");
                    }

                    @Override
                    public void OnCancelButtonClick() {
                        finish();
                        TaskManager.get(getBaseContext()).deleteTask(t);
                        Log.d(TAG, "canceled");
                        TaskManager.get(getBaseContext()).deleteTask(t);
                    }
                });
                return taskFragment;
            }

            @Override
            public int getCount() {
                return mTasks.size();
            }
        });

        for (int i = 0; i < mTasks.size(); i++) {
            if (mTasks.get(i).getId().equals(taskId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
