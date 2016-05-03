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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

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
