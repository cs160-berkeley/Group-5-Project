package com.example.david.pensieve_test;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class WatchToPhoneService extends Service implements GoogleApiClient.ConnectionCallbacks {
    private static final String TAG = "@>@>@>";
    private GoogleApiClient mWatchApiClient;
    private List<Node> nodes = new ArrayList<>();
    private String sendData = "nothing";

    @Override
    public void onCreate() {
        super.onCreate();
        //initialize the googleAPIClient for message passing

        mWatchApiClient = new GoogleApiClient.Builder( this )
                .addApi( Wearable.API )
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        Log.wtf(TAG, "why does it fail?");
                    }
                })
                .build();
        //and actually connect it
        mWatchApiClient.connect();
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        sendData = (String) intent.getExtras().get("/dataToPhone");

        Log.d(TAG, "what is sendData? " + sendData);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWatchApiClient.disconnect();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override //alternate method to connecting: no longer create this in a new thread, but as a callback
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "in onconnected");

        Log.d(TAG, "in Connected , what is sendData? " + sendData);

        Wearable.NodeApi.getConnectedNodes(mWatchApiClient)
                .setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
                    @Override
                    public void onResult(NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
                        nodes = getConnectedNodesResult.getNodes();
                        Log.d(TAG, "found nodes");
                        //when we find a connected node, we populate the list declared above
                        //finally, we can send a message
                        if(sendData.equals("nothing")) {
                            sendMessage("/send_nothing", "nothing");
                        } else {
                            sendMessage("/send_data", sendData);
                        }
                        Log.wtf(TAG, "sent");

                        onDestroy(); //destroy
                    }
                });
    }

    @Override //we need this to implement GoogleApiClient.ConnectionsCallback
    public void onConnectionSuspended(int i) {}


    private void sendMessage(final String path, final String text ) {
        for (Node node : nodes) {
            Wearable.MessageApi.sendMessage(mWatchApiClient, node.getId(), path, text.getBytes());
        }
    }

}
