package com.nerdery.umbrella.widget;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.MediumTest;

import static com.nerdery.umbrella.widget.DynamicGridLayoutManager.HORIZONTAL;
import static com.nerdery.umbrella.widget.DynamicGridLayoutManager.VERTICAL;

/**
 * Tests parts of {@link DynamicGridLayoutManager}.
 *
 * @author Phil Brown
 * @since 9:31 AM Jul 26, 2015
 */
public class DynamicGridLayoutManagerTest extends InstrumentationTestCase {

    private Context mContext;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mContext = getInstrumentation().getContext();
    }

    @Override
    public void tearDown() throws Exception {
        mContext = null;
        super.tearDown();
    }

    @UiThreadTest
    @MediumTest
    public void testConstructors() throws Exception {
        for (int i = 1; i < 10; i++) {
            new DynamicGridLayoutManager(mContext, i);
            new DynamicGridLayoutManager(mContext, i, HORIZONTAL, true);
            new DynamicGridLayoutManager(mContext, i, HORIZONTAL, false);
            new DynamicGridLayoutManager(mContext, i, VERTICAL, true);
            new DynamicGridLayoutManager(mContext, i, VERTICAL, false);
        }
    }
}
