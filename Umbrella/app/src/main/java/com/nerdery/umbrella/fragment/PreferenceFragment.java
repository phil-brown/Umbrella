package com.nerdery.umbrella.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
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

        EditTextPreference zipCodePreference = (EditTextPreference) getPreferenceScreen().findPreference(getString(R.string.key_zipcode_preference));

        zipCodePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean validZipCode = true;

                if (newValue == null) {
                    validZipCode = false;
                }
                else if (!(newValue instanceof String)) {
                    validZipCode = false;
                }
                else {
                    String value = (String) newValue;
                    try {
                        int intValue = Integer.parseInt(value);
                        if (intValue < 10000 || intValue > 99999) {
                            validZipCode = false;
                        }
                    } catch (NumberFormatException e) {
                        validZipCode = false;
                    }
                }

                if (!validZipCode) {
                    Activity activity = getActivity();
                    if (activity != null && isAdded()) {
                        new AlertDialog.Builder(activity)
                                .setTitle(R.string.invalid_input)
                                .setMessage(R.string.invalid_zipcode)
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                }
                return validZipCode;
            }
        });
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
