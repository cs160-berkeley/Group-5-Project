package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by david on 4/20/16.
 */
public class DeleteTask extends Activity {
    private String TAG = "@>@>@>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_member_delete_action_activity);
    }

    public void goBack(View view){
        Intent i = new Intent(DeleteTask.this, FamilyMemberMainActivity.class);
        startActivity(i);
    }
}
