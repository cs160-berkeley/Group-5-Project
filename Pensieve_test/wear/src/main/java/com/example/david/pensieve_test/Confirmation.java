package com.example.david.pensieve_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by david on 4/19/16.
 */
public class Confirmation extends Activity{
    private final String TAG = "@>@>@>";

    private String todoTask = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            todoTask = extras.getString("/task_item");
            String[] lst = todoTask.split("@@@");

            TextView task_item = (TextView) findViewById(R.id.task_item);
            task_item.setText(lst[0]);
        }

        CircledImageView parag = (CircledImageView) findViewById(R.id.reminder);
        parag.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(Confirmation.this, "right", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Confirmation.this, cantRemember.class);
                if (todoTask != "") {
                    intent.putExtra("/dont_remember", todoTask);
                    Log.d(TAG, "title is not null " + todoTask);
                } else {
                    intent.putExtra("/dont_remember", "NOTHING");
                    Log.d(TAG, "title is empty");
                }
                startActivity(intent);
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(Confirmation.this, "left", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Confirmation.this, cantRemember.class);
                if (todoTask != "") {
                    intent.putExtra("/dont_remember", todoTask);
                    Log.d(TAG, "title is not null " + todoTask);
                } else {
                    intent.putExtra("/dont_remember", "NOTHING");
                    Log.d(TAG, "title is empty");
                }
                startActivity(intent);
            }
        });

    }


}
