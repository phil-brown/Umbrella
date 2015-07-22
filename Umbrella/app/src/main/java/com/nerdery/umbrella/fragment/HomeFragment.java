package com.nerdery.umbrella.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nerdery.umbrella.R;

/**
 * This fragment shows the weather conditions.
 *
 * @author Phil Brown
 * @since 9:52 AM Jul 22, 2015
 */
public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        return view;
    }
}
