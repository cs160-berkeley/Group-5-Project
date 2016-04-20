package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by david on 4/19/16.
 */
public class FamilyMemberGraphDayMenuOpenActivity extends Activity {

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        setContentView(R.layout.family_member_graph_day_menu_open_activity);
    }


    public void onStartFamilyMemberGraphWeek(View view) {
        Intent startIntent = new Intent(this, FamilyMemberGraphWeekActivity.class);
        startActivity(startIntent);

    }

}
