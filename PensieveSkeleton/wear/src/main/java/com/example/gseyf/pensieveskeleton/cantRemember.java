package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by david on 4/19/16.
 */
public class cantRemember extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_2);

        RelativeLayout parag = (RelativeLayout) findViewById(R.id.cant_remember);
        parag.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            public void onSwipeRight() {
            }

            public void onSwipeLeft() {
                Intent intent = new Intent(cantRemember.this, cantRemember.class);
                startActivity(intent);
            }
        });
    }

//    public void forgot(View view) {
//        Intent startIntent = new Intent(this, .class);
//        startActivity(startIntent);
//    }
}
