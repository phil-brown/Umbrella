package com.nerdery.umbrella.fragment;

import android.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.view.LayoutInflater;
import android.view.View;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.activity.MainActivity;

/**
 * HomeFragment tests
 *
 * @author Phil Brown
 * @since 9:22 AM Jul 26, 2015
 */
public class HomeFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;

    public HomeFragmentTest() {
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
        FragmentManager fragmentManager = mMainActivity.getFragmentManager();
        assertNotNull(fragmentManager);
        HomeFragment fragment = (HomeFragment) fragmentManager.findFragmentById(R.id.container);
        assertNotNull("HomeFragment not shown", fragment);

        View view = fragment.getView();
        assertNotNull(view);
        HomeFragment.ViewHolder viewHolder = (HomeFragment.ViewHolder) view.getTag();
        assertNotNull(viewHolder);
        assertNotNull(viewHolder.currentCondition);
        assertNotNull(viewHolder.city);
        assertNotNull(viewHolder.temperature);
        assertNotNull(viewHolder.condition);
        assertNotNull(viewHolder.settings);
    }
}
