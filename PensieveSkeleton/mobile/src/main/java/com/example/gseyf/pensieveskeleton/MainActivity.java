package com.example.gseyf.pensieveskeleton;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartPatientLogin(View view) {
        Intent startIntent = new Intent(this, LoginOrCreateAccount.class);
        startIntent.putExtra("user", "patient");
        startActivity(startIntent);
    }

    public void onStartFamilyMemberLogin(View view) {
        Intent startIntent = new Intent(this, LoginOrCreateAccount.class);
        startIntent.putExtra("user", "family_member");
        startActivity(startIntent);
    }
}
