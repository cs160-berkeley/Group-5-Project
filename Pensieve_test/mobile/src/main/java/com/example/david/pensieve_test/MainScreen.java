package com.example.david.pensieve_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.example.david.pensieve_test.utils.TypeFacesUtils;

/**
 * Created by david on 4/17/16.
 */
public class MainScreen extends AppCompatActivity {
    private Button mPatient;
    private Button mFamilyMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.new_task_c);
        setContentView(R.layout.activity_main);

        mPatient = (Button) findViewById(R.id.patient_login);
        mPatient.setTextSize(TypedValue.COMPLEX_UNIT_PX, TypeFacesUtils.dpToPx(22));
        mPatient.setTypeface(TypeFacesUtils.getTypeface(this, "fonts/Roboto-Regular.ttf"));

        mPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, TaskListActivity.class);
                intent.putExtra("role", "0");
                startActivity(intent);
            }
        });
//        mPatient.setTextSize(TypedValue.COMPLEX_UNIT_PX, TypeFacesUtils.dpToPx(20));
//        mPatient.setTypeface(TypeFacesUtils.getTypeface(this, "fonts/Roboto-Bold.ttf"));

        mFamilyMember = (Button) findViewById(R.id.family_member_login);
        mFamilyMember.setTextSize(TypedValue.COMPLEX_UNIT_PX, TypeFacesUtils.dpToPx(22));
        mFamilyMember.setTypeface(TypeFacesUtils.getTypeface(this, "fonts/Roboto-Regular.ttf"));

        mFamilyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, TaskListActivity.class);
                intent.putExtra("role", "1");
                startActivity(intent);
            }
        });
    }

}