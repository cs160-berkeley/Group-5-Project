package com.example.david.pensieve_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by david on 5/1/16.
 */
public class Notification extends Activity {
    private String data;
    TextView title;
    TextView time;
    TextView ampm;

    @Override
    public void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.family_member_notification);

        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time1);
        ampm = (TextView) findViewById(R.id.time2);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            data = extras.getString("/send_data");
            String[] lst = data.split("@@@");

            title.setText(lst[0]);
            time.setText(lst[1]);
            ampm.setText(lst[2]);

        }


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
