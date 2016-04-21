package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

/**
 * Created by david on 4/19/16.
 */
public class FamilyMemberMainActivity extends Activity {
    //Timer timer = new Timer();

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        setContentView(R.layout.family_member_main_activity);

        TextView txtView = (TextView) findViewById(R.id.familymemberbreakfast);
        txtView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),
                        "You have pressed it long :)", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent startIntent = new Intent(FamilyMemberMainActivity.this, FamilyMemberGraphDayActivity.class);
                startActivity(startIntent);
            }
        });

    }

    public void onStartFamilyMemberAddTaskActivity(View view) {
        Intent startIntent = new Intent(this, FamilyMemberAddTaskActivity.class);
        startActivity(startIntent);
    }

    public void onClickSettings(View view){
        Intent startIntent = new Intent(this, SettingsExpanded.class);
        startActivity(startIntent);
    }



}
