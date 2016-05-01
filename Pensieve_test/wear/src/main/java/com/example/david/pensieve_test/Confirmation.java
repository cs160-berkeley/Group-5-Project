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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            String todoTask = extras.getString("/task_item");

            TextView task_item = (TextView) findViewById(R.id.task_item);
            task_item.setText(todoTask);
        }

        CircledImageView parag = (CircledImageView) findViewById(R.id.reminder);
        parag.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(Confirmation.this, "right", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Confirmation.this, cantRemember.class);
                startActivity(intent);
                Log.d(TAG, "swipe right");
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(Confirmation.this, "left", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Confirmation.this, cantRemember.class);
                startActivity(intent);
                Log.d(TAG, "swipe left");
            }
        });

    }


}
