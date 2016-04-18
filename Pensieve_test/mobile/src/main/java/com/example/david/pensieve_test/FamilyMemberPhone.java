package com.example.david.pensieve_test;

import android.support.v4.app.Fragment;

/**
 * Created by david on 4/17/16.
 */
public class FamilyMemberPhone extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new FamilyMemberFragment();
    }
}
