package com.nerdery.umbrella.ui;

import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.view.View;

import com.nerdery.umbrella.R;

/**
 * Tests the Weather Condition cell creation
 *
 * @author Phil Brown
 * @since 9:23 AM Jul 26, 2015
 */
public class ForecastConditionViewHolderTest extends InstrumentationTestCase {

    @UiThreadTest
    public void testConstructor() throws Exception {
        View cell = View.inflate(getInstrumentation().getContext(), R.layout.cell_weather, null);
        ForecastConditionViewHolder holder = new ForecastConditionViewHolder(cell);
        assertNotNull(holder.day);
        assertNotNull(holder.grid);
    }
}
