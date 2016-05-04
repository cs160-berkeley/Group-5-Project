package com.example.david.pensieve_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.wearable.view.CircledImageView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by david on 4/19/16.
 */
public class yes extends Activity {
    private final String TAG = "@>@>@>";

    private Button mButton;
    private String taskId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yes);
        Log.d("YES", "On the yes watch screen");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            taskId = extras.getString("taskId");
            Log.d("YES", "Received extras: taskId=" + taskId);
        }

        mButton = (Button) findViewById(R.id.check);
        mButton.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
                    mButton.setBackgroundResource(R.drawable.check_changed);
                } else {
                    mButton.setBackgroundResource(R.drawable.check);
                    Log.d("YES", "Yes button clicked on watch. Sending to phone: taskId=" + taskId);

                    Intent i = new Intent(getBaseContext(), WatchToPhoneService.class);
                    i.putExtra("/dataToPhone", "nothing");
                    i.putExtra("taskId", taskId);
                    startService(i);
                }
                return true;
            }
        });

        CircledImageView parag = (CircledImageView) findViewById(R.id.yes);
        parag.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                //Toast.makeText(yes.this, "right", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(yes.this, cantRemember.class);
                startActivity(intent);
                Log.d(TAG, "swipe right");
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();

            }
        });
    }

}
