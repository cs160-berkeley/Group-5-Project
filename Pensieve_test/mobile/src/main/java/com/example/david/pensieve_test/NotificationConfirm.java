package com.example.david.pensieve_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/*
The final family member notification that has one dismiss button
that brings the family member back to the task list
 */
public class NotificationConfirm extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_confirm);
        Intent intent = getIntent();

        TextView time = (TextView) findViewById(R.id.time1);
        TextView ampm = (TextView) findViewById(R.id.time2);
        TextView title = (TextView) findViewById(R.id.title);
        TextView handled = (TextView) findViewById(R.id.handled_time);

        time.setText(intent.getStringExtra("EXTRA_TIME"));
        title.setText(intent.getStringExtra("EXTRA_TITLE"));
        ampm.setText(intent.getStringExtra("EXTRA_AMPM"));
        handled.setText("at " + intent.getStringExtra("EXTRA_HANDLED"));

        Button ok = (Button) findViewById(R.id.btn_dismiss);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), TaskListActivity.class);
                i.putExtra("role", "1");
                startActivity(i);
            }
        });
    }

}
