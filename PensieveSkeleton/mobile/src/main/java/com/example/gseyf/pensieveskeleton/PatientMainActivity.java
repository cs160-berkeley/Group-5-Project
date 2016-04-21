package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

/**
 * Created by david on 4/19/16.
 */
public class PatientMainActivity extends Activity{
    private String TAG = "@>@>@>";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_main_activity);

        //timer.schedule();
        Handler handler = new Handler();
        Log.d(TAG, "starting countdown");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startWatch();
                Log.d(TAG, "10 seconds passed");
            }
        }, 10000); //10sec. 10,000 msec

    }

    public void onStartPatientAddNoteActivity(View view) {
        Intent startIntent = new Intent(this, PatientAddNoteActivity.class);
        startActivity(startIntent);

    }

    private void startWatch(){

        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
        //sendIntent.putExtra("dataToWatch", watchToData);
        startService(sendIntent);
    }

}
