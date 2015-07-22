package com.nerdery.umbrella.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.fragment.HomeFragment;

/**
 * Main Activity
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentById(R.id.container) == null) {
            fragmentManager.beginTransaction().replace(R.id.container, HomeFragment.newInstance(), null).commitAllowingStateLoss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
