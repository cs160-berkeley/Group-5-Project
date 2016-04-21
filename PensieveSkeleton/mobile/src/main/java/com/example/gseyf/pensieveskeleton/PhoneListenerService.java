package com.example.gseyf.pensieveskeleton;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by david on 3/1/16.
 */
public class PhoneListenerService extends WearableListenerService {

    private static final String TAG = "@>@>@>@>";
    private static final String NEED_HELP = "/need_help";
    private static final String GOOD = "/good";


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.wtf(TAG, "in PhoneListenerService, got: " + messageEvent.getPath());

        if( messageEvent.getPath().equalsIgnoreCase(NEED_HELP) ) {
//            String name = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//            Log.d(TAG, "in phone listener: " + name);

            Intent intent = new Intent(this, Notification.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if( messageEvent.getPath().equalsIgnoreCase(GOOD) ) {
//            String zipcode = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            Intent i = new Intent(this, PatientMainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } else {
            Log.d(TAG, "does not match");
            super.onMessageReceived(messageEvent);
        }
        Log.wtf(TAG, "wtf?");
    }
}
