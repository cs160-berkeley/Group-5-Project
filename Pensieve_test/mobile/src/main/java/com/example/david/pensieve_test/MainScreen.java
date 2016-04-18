package com.example.david.pensieve_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by david on 4/17/16.
 */
public class MainScreen extends AppCompatActivity {
    private Button mPatient;
    private Button mFamilyMember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mPatient = (Button) findViewById(R.id.patient_login);
        mPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, PatientPhone.class);
                startActivity(intent);
            }
        });


        mFamilyMember = (Button) findViewById(R.id.family_member_login);
        mFamilyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, FamilyMemberPhone.class);
                startActivity(intent);
            }
        });
    }

}
