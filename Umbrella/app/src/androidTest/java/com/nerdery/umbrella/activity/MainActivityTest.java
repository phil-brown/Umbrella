package com.nerdery.umbrella.activity;

import android.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.fragment.HomeFragment;

/**
 * Tests the {@link MainActivity}.
 *
 * @author Phil Brown
 * @since 7:23 AM Jul 24, 2015
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;

    public MainActivityTest() {
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

    /**
     * Test initial Fragment setup
     * @throws Exception
     */
    public void testHomeFragment() throws Exception {
        FragmentManager fragmentManager = mMainActivity.getFragmentManager();
        assertNotNull(fragmentManager);
        HomeFragment fragment = (HomeFragment) fragmentManager.findFragmentById(R.id.container);
        assertNotNull("HomeFragment not shown", fragment);
    }
}
