package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by david on 4/20/16.
 */
public class FamilyMemberCreateAccount extends Activity {

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        setContentView(R.layout.family_member_account_screen);
    }

    public void onFamilyMemberCreateAccount(View view) {
        Intent startIntent = new Intent(this, FamilyMemberMainActivity.class);
        startActivity(startIntent);
    }


}
