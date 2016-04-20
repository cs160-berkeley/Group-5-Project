package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by david on 4/20/16.
 */
public class LoginOrCreateAccount extends Activity {
    private String mUser;
    private String TAG = "@>@>@>";

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.login_create_account);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            mUser = extras.getString("user");
        }
    }

    public void onLogin(View view) {

        Intent startIntent = new Intent(this, Login.class);
        startIntent.putExtra("user", mUser);
        startActivity(startIntent);

    }

    public void onCreateAccount(View view) {

        Log.d(TAG, mUser);

        if(mUser.equals("patient")) {
            Log.d(TAG, "start PatientCreateAccount");
            Intent i = new Intent(this, PatientCreateAccount.class);
            startActivity(i);
        } else if (mUser.equals("family_member")){
            Intent startIntent = new Intent(this, FamilyMemberCreateAccount.class);
            startActivity(startIntent);
        }

    }
}
