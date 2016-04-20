package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by david on 4/19/16.
 */
public class FamilyMemberGraphWeekActivity extends Activity {

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        setContentView(R.layout.family_member_graph_week_activity);
    }

    public void onStartFamilyMemberGraphWeekGoBack(View view) {
        finish();
    }
}
