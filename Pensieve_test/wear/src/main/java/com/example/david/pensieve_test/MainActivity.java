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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircledImageView parag = (CircledImageView) findViewById(R.id.initial_reminder);
        parag.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, Confirmation.class);
                startActivity(intent);
                Log.d(TAG, "swipe right");
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, Confirmation.class);
                startActivity(intent);
                Log.d(TAG, "swipe left");
            }
        });

    }
}
