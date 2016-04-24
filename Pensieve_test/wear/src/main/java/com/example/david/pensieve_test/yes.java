package com.example.david.pensieve_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by david on 4/19/16.
 */
public class yes extends Activity {
    private final String TAG = "@>@>@>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yes);

        CircledImageView parag = (CircledImageView) findViewById(R.id.yes);
        parag.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(yes.this, "right", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(yes.this, cantRemember.class);
                startActivity(intent);
                Log.d(TAG, "swipe right");
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(yes.this, "left", Toast.LENGTH_SHORT).show();

                // Intent intent = new Intent(cantRemember.this, cantRemember.class);
                // startActivity(intent);
                Log.d(TAG, "swipe left");

            }
        });
    }

    public void confirm(View view){
        Intent startIntent = new Intent(this, WatchToPhoneService.class);
        startIntent.putExtra("status", "confirm");
        startService(startIntent);
    }
}
