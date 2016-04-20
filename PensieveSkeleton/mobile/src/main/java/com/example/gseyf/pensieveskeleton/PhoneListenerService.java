package com.example.gseyf.pensieveskeleton;

import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by david on 3/1/16.
 */
public class PhoneListenerService extends WearableListenerService {

    private static final String TAG = "@>@>@>@>";
    private static final String NAME = "/send_name";
    private static final String ZIPCODE = "/send_zipcode";


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.wtf(TAG, "in PhoneListenerService, got: " + messageEvent.getPath());

        if( messageEvent.getPath().equalsIgnoreCase(NAME) ) {
            String name = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Log.d(TAG, "in phone listener: " + name);

//            Intent intent = new Intent(this, DetailedView.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra("name", name);
//            startActivity(intent);

        } else if( messageEvent.getPath().equalsIgnoreCase(ZIPCODE) ) {
            String zipcode = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            Log.d(TAG, "send zipcode from PListener: " + zipcode);

//            Intent i = new Intent(this, CongressionalView.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.putExtra("zipcode", zipcode);
//            startActivity(i);
        } else {
            Log.d(TAG, "does not match");
            super.onMessageReceived(messageEvent);
        }
        Log.wtf(TAG, "wtf?");
    }
}
