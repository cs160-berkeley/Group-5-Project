package com.example.david.pensieve_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/*
The final family member notification that has one dismiss button
that brings the family member back to the task list
 */
public class NotificationConfirm extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_confirm);
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
