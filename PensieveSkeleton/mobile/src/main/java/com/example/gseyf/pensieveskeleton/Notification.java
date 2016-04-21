package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by david on 4/20/16.
 */
public class Notification extends Activity {

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.family_member_notification_activity);
    }

    public void hesOk(View view){
        Intent i = new Intent(this, FamilyMemberMainActivity.class);
        startActivity(i);
    }

    public void call(View view){
        Intent i = new Intent(this, NotificationResolved.class);
        startActivity(i);
    }
}
