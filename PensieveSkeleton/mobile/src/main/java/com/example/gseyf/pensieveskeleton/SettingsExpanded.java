package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by david on 4/20/16.
 */
public class SettingsExpanded extends Activity {

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.settings_expanded);
    }

    public void onSettings(View view) {
        Intent startIntent = new Intent(this, Settings.class);
        startActivity(startIntent);
    }

    public void onOverviewGraph(View view) {
        Intent startIntent = new Intent(this, OverviewGraph.class);
        startActivity(startIntent);
    }

    public void onLogout(View view){
        Intent startIntent = new Intent(this, MainActivity.class);
        startActivity(startIntent);
    }
}
