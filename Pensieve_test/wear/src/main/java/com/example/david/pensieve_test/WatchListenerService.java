package com.example.david.pensieve_test;

/**
 * Created by david on 3/1/16.
 */
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;


public class WatchListenerService extends WearableListenerService {
    private static final String TAG = "@>@>@>@>";
    private static final String dataToWatch = "/dataToWatch";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d(TAG, "in WatchListenerService, got: " + messageEvent.getPath());

        if( messageEvent.getPath().equalsIgnoreCase(dataToWatch) ) {
            String data = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.putExtra("dataToWatch", data);

            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }

}
