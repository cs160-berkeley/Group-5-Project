package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainWatchScreen extends Activity {
    private final String TAG = "@>@>@>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_confirmation);

        DismissOverlayView parag = (DismissOverlayView) findViewById(R.id.initial_reminder);
        parag.setOnTouchListener(new OnSwipeTouchListener(MainWatchScreen.this) {

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(MainWatchScreen.this, "right", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainWatchScreen.this, Confirmation.class);
                startActivity(intent);
                Log.d(TAG, "swipe right");
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();

                Toast.makeText(MainWatchScreen.this, "left", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainWatchScreen.this, Confirmation.class);
                startActivity(intent);
                Log.d(TAG, "swipe left");
            }
        });

    }


}
