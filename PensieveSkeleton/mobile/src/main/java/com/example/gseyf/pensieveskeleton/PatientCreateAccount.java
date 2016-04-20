package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by david on 4/20/16.
 */
public class PatientCreateAccount extends Activity {
    private String TAG = "@>@>@>";

    @Override
    public void onCreate(Bundle b) {

        Log.wtf(TAG, "WTF");

        super.onCreate(b);
        setContentView(R.layout.patient_account_screen);
    }

    public void onPatientCreateAccount(View view) {
        Intent startIntent = new Intent(this, PatientMainActivity.class);
        startActivity(startIntent);
    }

}
