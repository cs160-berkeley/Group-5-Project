package com.example.david.pensieve_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by david on 5/1/16.
 */
public class Notification extends Activity {

    @Override
    public void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.family_member_notification);

        Button ok = (Button) findViewById(R.id.btn_theyre_OK);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), TaskListActivity.class);
                startActivity(i);
            }
        });

        Button call = (Button) findViewById(R.id.btn_call_them);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), TaskListActivity.class);
                startActivity(i);
            }
        });
    }
}
