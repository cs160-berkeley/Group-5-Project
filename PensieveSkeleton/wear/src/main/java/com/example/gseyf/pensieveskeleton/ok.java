package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by david on 4/19/16.
 */
public class ok extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_3);

        DismissOverlayView parag = (DismissOverlayView) findViewById(R.id.yes);
        parag.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            public void onSwipeRight() {
                Intent intent = new Intent(ok.this, cantRemember.class);
                startActivity(intent);
            }

            public void onSwipeLeft() {

            }
        });
    }

//    public void confirm(View view) {
//        Intent startIntent = new Intent(this, FamilyMemberMainActivity.class);
//        startActivity(startIntent);
//    }


}
