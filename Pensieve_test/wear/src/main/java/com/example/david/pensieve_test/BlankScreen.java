package com.example.david.pensieve_test;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by david on 5/1/16.
 */
public class BlankScreen extends Activity {
    public static Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.blankscreen);
    }
}
