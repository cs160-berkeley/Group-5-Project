package com.example.david.pensieve_test;

import android.support.v4.app.Fragment;

/**
 * Created by david on 4/17/16.
 */
public class PatientPhone extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PatientFragment();
    }
}
