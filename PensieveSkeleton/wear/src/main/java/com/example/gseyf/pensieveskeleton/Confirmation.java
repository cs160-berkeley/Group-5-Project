package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.widget.LinearLayout;

/**
 * Created by david on 4/19/16.
 */
public class Confirmation extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_1);

        DismissOverlayView parag = (DismissOverlayView) findViewById(R.id.reminder);
        parag.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            public void onSwipeRight() {
            }

            public void onSwipeLeft() {
                Intent intent = new Intent(Confirmation.this, cantRemember.class);
                startActivity(intent);
            }
        });

    }



}
