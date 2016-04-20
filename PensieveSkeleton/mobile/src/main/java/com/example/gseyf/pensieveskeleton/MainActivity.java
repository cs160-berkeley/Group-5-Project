package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onStartPatientMainActivity(View view) {
        Intent startIntent = new Intent(this, PatientMainActivity.class);
        startActivity(startIntent);

    }

    public void onStartFamilyMemberMainActivity(View view) {
        Intent startIntent = new Intent(this, FamilyMemberMainActivity.class);
        startActivity(startIntent);
    }
}
