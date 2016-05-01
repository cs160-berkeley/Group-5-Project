package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.DismissOverlayView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by david on 4/19/16.
 */
public class cantRemember extends Activity{
    private final String TAG = "@>@>@>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_2);

        CircledImageView parag = (CircledImageView) findViewById(R.id.cant_remember);
        parag.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(cantRemember.this, "right", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(cantRemember.this, Confirmation.class);
                startActivity(intent);
                Log.d(TAG, "swipe right");
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(cantRemember.this, "left", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(cantRemember.this, yes.class);
                startActivity(intent);

                Log.d(TAG, "swipe left");
            }
        });
    }


    public void forgot(View view){
        Intent startIntent = new Intent(this, WatchToPhoneService.class);
        startIntent.putExtra("status", "needHelp");
        startService(startIntent);
    }


}
