package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by david on 4/19/16.
 */
public class FamilyMemberAddTaskTimePickerActivity extends Activity {

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        setContentView(R.layout.family_member_add_task_timepicker_activity);
    }

    public void onStartFamilyMemberAddTaskTimePickerActivity(View view) {
        Intent startIntent = new Intent(this, FamilyMemberAddTaskTimePickerActivity.class);
        startActivity(startIntent);
    }

    public void onStartFamilyMemberAddTaskTimePickerGoBack(View view) {
        finish();
    }


}
