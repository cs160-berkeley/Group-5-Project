package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by david on 4/19/16.
 */
public class FamilyMemberAddTaskActivity extends Activity {

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
    }

    public void onStartFamilyMemberAddTaskTimePickerActivity(View view) {
        Intent startIntent = new Intent(this, FamilyMemberAddTaskTimePickerActivity.class);
        startActivity(startIntent);
    }

}
