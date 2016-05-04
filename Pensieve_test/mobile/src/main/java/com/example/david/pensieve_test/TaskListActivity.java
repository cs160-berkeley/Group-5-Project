package com.example.david.pensieve_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by david on 4/17/16.
 */
public class TaskListActivity extends AppCompatActivity {
    private String data;
    private Tasks mTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        Log.d("TASKLISTACTIVITY", "Received task complete notification");

        if (extras != null) {
            data = extras.getString("/send_data");
            if (data != null) {
                String[] lst = data.split("@@@");

                TaskManager taskManager = TaskManager.get(getApplicationContext());
                Log.d("TASKLISTACTIVITY", "Received UUID: " + lst[0]);
                mTask = taskManager.getTask(java.util.UUID.fromString(lst[0]));
                mTask.setCompleted(1);
                taskManager.updateTask(mTask);
            }
        }

        if (fragment == null) {
            Intent intent = getIntent();
            Log.d("@>@>@>", "what is role? " + intent.getStringExtra("role"));
            int role = (int) Integer.parseInt(intent.getStringExtra("role"));
            fm.beginTransaction()
                    .add(R.id.fragment_container, FamilyMemberFragment.newInstance(role))
                    .commit();
        }
    }
}
