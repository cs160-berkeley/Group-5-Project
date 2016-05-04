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
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by david on 4/19/16.
 */
public class cantRemember extends Activity{
    private final String TAG = "@>@>@>";

    private String todoTask = "";
    private Boolean set = Boolean.FALSE;
    private String taskId = "";

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cant_remember);
        Log.d("CANTREMEMBER", "On can't remember watch screen");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //TODO: get taskId

        if (extras != null) {
            todoTask = extras.getString("/dont_remember");
            taskId = extras.getString("taskId");
            Log.d("CANTREMEMBER", "Received extras: taskId=" + taskId + " and data=" + todoTask);
            set = Boolean.TRUE;
        }

        mButton = (Button) findViewById(R.id.question);
        mButton.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
                    mButton.setBackgroundResource(R.drawable.question_changed);
                    Log.d(TAG, "pressed");
                } else {
                    mButton.setBackgroundResource(R.drawable.question);
                    if (set) {
                        Intent i = new Intent(getBaseContext(), WatchToPhoneService.class); //WToPService
                        i.putExtra("/dataToPhone", todoTask); //null
                        i.putExtra("taskId", taskId);
                        Log.d("CANTREMEMBER", "Clicked on can't remember button, sending to phone: taskId=" + taskId + " and data=" + todoTask);
                        startService(i);
                    }
                }
            return true;
            }
        });


        CircledImageView parag = (CircledImageView) findViewById(R.id.cant_remember);
        parag.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                //Toast.makeText(cantRemember.this, "right", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(cantRemember.this, Confirmation.class);
                startActivity(intent);
                Log.d(TAG, "swipe right");
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                //Toast.makeText(cantRemember.this, "left", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(cantRemember.this, yes.class);
                intent.putExtra("taskId", taskId);
                startActivity(intent);

                Log.d(TAG, "swipe left");
            }
        });
    }

}
