package com.example.david.pensieve_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private final String TAG = "@>@>@>";
    private String todoTask = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            todoTask = extras.getString("dataToWatch");
            String[] lst = todoTask.split("@@@");

            TextView task_item = (TextView) findViewById(R.id.watch_task_item);
            task_item.setText(lst[0]);

            TextView time_item = (TextView) findViewById(R.id.watch_time);
            time_item.setText(lst[1] + " " + lst[2]);
        }

        CircledImageView parag = (CircledImageView) findViewById(R.id.initial_reminder);
        parag.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, Confirmation.class);
                if (todoTask != "") {
                    intent.putExtra("/task_item", todoTask);
                    Log.d(TAG, "title is not null " + todoTask);
                } else {
                    intent.putExtra("/task_item", "NOTHING");
                    Log.d(TAG, "title is empty");
                }
                startActivity(intent);
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, Confirmation.class);
                if (todoTask != "") {
                    intent.putExtra("/task_item", todoTask);
                    Log.d(TAG, "title is not null " + todoTask);
                } else {
                    intent.putExtra("/task_item", "NOTHING");
                    Log.d(TAG, "title is empty");
                }
                startActivity(intent);
            }
        });

    }
}
