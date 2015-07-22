package com.nerdery.umbrella.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.fragment.HomeFragment;

/**
 * Main Activity. Changed from "ActionBarActivity", since the min sdk already supports new Fragments.
 */
public class MainActivity extends Activity {

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
