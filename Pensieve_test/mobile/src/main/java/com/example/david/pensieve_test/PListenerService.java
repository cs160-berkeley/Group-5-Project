package com.example.david.pensieve_test;

/**
 * Created by david on 3/1/16.
 */
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;


public class PListenerService extends WearableListenerService {
    private static final String TAG = "@>@>@>@>";
    private static final String DATA = "/send_data";
    private static final String NOTHING = "/send_nothing";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d(TAG, "in PListenerService, got: " + messageEvent.getPath());

        if( messageEvent.getPath().equalsIgnoreCase(DATA) ) {
            String data = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            Intent intent = new Intent(this, Notification.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(DATA, data);
            startActivity(intent);

            Log.d(TAG, "In PListener, sent data to Phone");

        } else if( messageEvent.getPath().equalsIgnoreCase(NOTHING) ) {
            String nothing = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            Intent i = new Intent(this, TaskListActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra(DATA, nothing);
            i.putExtra("role", "0"); // Goes to patient screen

            startActivity(i);
            Log.d(TAG, "In PListener, sent nothing to Phone");
        } else {
            Log.d(TAG, "does not match");
            super.onMessageReceived(messageEvent);
        }

    }

}
