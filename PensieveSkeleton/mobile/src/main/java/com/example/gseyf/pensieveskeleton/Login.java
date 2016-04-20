package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by david on 4/20/16.
 */
public class Login extends Activity {

    private String mUser;


    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            mUser = extras.getString("user");
        }

        setContentView(R.layout.login);
    }

    public void onLoginOK(View view) {

        if(mUser.equals("patient")) {
            Intent startIntent = new Intent(this, PatientMainActivity.class);
            startActivity(startIntent);
        } else if(mUser.equals("family_member")) {
            Intent startIntent = new Intent(this, FamilyMemberMainActivity.class);
            startActivity(startIntent);
        }

    }

}
