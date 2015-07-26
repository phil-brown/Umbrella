package com.nerdery.umbrella.fragment;

import android.app.FragmentManager;
import android.cts.util.PollingCheck;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.activity.MainActivity;

/**
 * Tests that the Preferences screen is shown correctly.
 *
 * @author Phil Brown
 * @since 9:22 AM Jul 26, 2015
 */
public class PreferenceFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;

    public PreferenceFragmentTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mMainActivity = getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        mMainActivity = null;
        super.tearDown();
    }

    @UiThreadTest
    public void testLayout() throws Exception {
        final FragmentManager fragmentManager = mMainActivity.getFragmentManager();
        assertNotNull(fragmentManager);
        HomeFragment fragment = (HomeFragment) fragmentManager.findFragmentById(R.id.container);
        assertNotNull("HomeFragment not shown", fragment);

        fragment.openPreferences();
        new PollingCheck() {
            @Override
            protected boolean check() {
                return (fragmentManager.findFragmentById(R.id.container) != null && fragmentManager.findFragmentById(R.id.container) instanceof PreferenceFragment);
            }
        }.run();
    }
}
