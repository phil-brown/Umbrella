package com.nerdery.umbrella.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nerdery.umbrella.R;

/**
 * Allows the user to configure what the app displays.
 *
 * @author Phil Brown
 * @since 10:19 AM Jul 22, 2015
 */
public class PreferenceFragment extends android.preference.PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            //TODO set Action bar text color in XML Style
            actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + getString(R.string.app_name) + "</font>"));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.PreferencesTheme);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        // inflate the layout using the cloned inflater, not default inflater
        return super.onCreateView(localInflater, container, savedInstanceState);
    }
}
