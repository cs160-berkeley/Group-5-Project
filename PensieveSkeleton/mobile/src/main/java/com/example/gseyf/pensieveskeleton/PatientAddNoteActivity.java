package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by david on 4/19/16.
 */
public class PatientAddNoteActivity extends Activity {

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.patient_add_note_activity);
    }

    public void onStartPatientAddNoteGoBack(View view) {
        finish();
    }
}
