package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by david on 4/19/16.
 */
public class PatientMainActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_main_activity);

    }

    public void onStartPatientAddNoteActivity(View view) {
        Intent startIntent = new Intent(this, PatientAddNoteActivity.class);
        startActivity(startIntent);

    }

}
