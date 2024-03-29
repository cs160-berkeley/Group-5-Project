package com.example.gseyf.pensieveskeleton;

/**
 * Created by david on 3/3/16.
 */

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by david on 3/1/16.
 */
public class WatchToPhoneService extends Service {

    private String status;

    private static final String TAG = "@>@>@>@>";
    // private GoogleApiClient mWatchApiClient;
    final Service _this = this;

    @Override
    public void onCreate() {
        super.onCreate();
//        mWatchApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Wearable.API)
//                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
//                    @Override
//                    public void onConnected(Bundle connectionHint) {
//                    }
//
//                    @Override
//                    public void onConnectionSuspended(int cause) {
//                    }
//                }).build();
        Log.d(TAG, "in watch to phone");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // mWatchApiClient.disconnect();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extras = intent.getExtras();

        Log.d(TAG, "WtoP: on start command");

        this.status = extras.getString("status");

        Log.d(TAG, this.status);

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(status.equals("needHelp")){
                    WatchToPhoneService service = new WatchToPhoneService();
                    service.sendMessage("/need_help", status);
                    Log.d(TAG, "sent need Help");
                } else {
                    WatchToPhoneService service = new WatchToPhoneService();
                    service.sendMessage("/good", status);
                    Log.d(TAG, "sent good");
                }
                Log.wtf(TAG, "sent");
            }
        }).start();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }

    public void sendMessage(final String path, final String text){
        Log.d(TAG, "Sending...");

        final GoogleApiClient mWatchApiClient = new GoogleApiClient.Builder(this.getBaseContext())
                .addApi(Wearable.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                    }

                    @Override
                    public void onConnectionSuspended(int cause) {
                    }
                }).build();

        new Thread(new Runnable() {
            @Override
            public void run() {

                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes(mWatchApiClient).await();
                Log.d(TAG, "nodes " + nodes.toString());
                for (Node node : nodes.getNodes()) {
                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                            mWatchApiClient, node.getId(), path, text.getBytes()).await();
                    Log.d(TAG, "result " + result.toString());
                }
            }
        }).start();
    }
}