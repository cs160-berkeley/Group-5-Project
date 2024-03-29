package com.example.david.pensieve_test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by david on 5/1/16.
 */
public class Notification extends Activity {
    private String data;
    TextView title;
    TextView time;
    TextView ampm;
    Tasks mTask;

    @Override
    public void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.family_member_notification);

        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time1);
        ampm = (TextView) findViewById(R.id.time2);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Log.d("NOTIFICATION", "Received Can't remember notification");

        if (extras != null) {
            data = extras.getString("/send_data");
            String[] lst = data.split("@@@");

            title.setText(lst[0]);
            time.setText(lst[1]);
            ampm.setText(lst[2]);
            TaskManager taskManager = TaskManager.get(getApplicationContext());
            mTask = taskManager.getTask(java.util.UUID.fromString(lst[3]));
            mTask.setCompleted(0);
            taskManager.updateTask(mTask);
        }

        Button ok = (Button) findViewById(R.id.btn_theyre_OK);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), NotificationConfirm.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_TITLE",title.getText().toString());
                extras.putString("EXTRA_TIME", time.getText().toString());
                extras.putString("EXTRA_AMPM", ampm.getText().toString());
                Date handledDate = FamilyMemberFragment.getCurrentTime();
                DateFormat handledFormat = new SimpleDateFormat("H:mm");
                String handled = handledFormat.format(handledDate);
                extras.putString("EXTRA_HANDLED", handled);
                i.putExtras(extras);
                //i.putExtra("role", "0");
                startActivity(i);
            }
        });

        Button call = (Button) findViewById(R.id.btn_call_them);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:8675309"));
                startActivity(i);
            }
        });
    }
}
