package com.nerdery.umbrella.adapter;

import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.view.View;

import com.nerdery.umbrella.model.ForecastCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tests adapter methods
 *
 * @author Phil Brown
 * @since 9:14 AM Jul 26, 2015
 */
public class ForecastGridAdapterTest extends InstrumentationTestCase {

    private ForecastCondition[] array;
    private ForecastGridAdapter adapter;
    private int index = -1;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        array = new ForecastCondition[30];
        ForecastCondition forecastCondition;
        for (int i = 6; i < 12; i++) {
            forecastCondition = new ForecastCondition();
            forecastCondition.displayTime = i + ":00 PM";
            forecastCondition.tempFahrenheit = i*new Random().nextInt(100);
            add(forecastCondition);
        }
        forecastCondition = new ForecastCondition();
        forecastCondition.displayTime = "12:00 AM";
        forecastCondition.tempFahrenheit = new Random().nextInt(100);
        add(forecastCondition);

        for (int i = 1; i < 12; i++) {
            forecastCondition = new ForecastCondition();
            forecastCondition.displayTime = i + ":00 AM";
            forecastCondition.tempFahrenheit = i*new Random().nextInt(100);
            add(forecastCondition);
        }
        forecastCondition = new ForecastCondition();
        forecastCondition.displayTime = "12:00 PM";
        forecastCondition.tempFahrenheit = new Random().nextInt(100);
        add(forecastCondition);

        for (int i = 1; i < 12; i++) {
            forecastCondition = new ForecastCondition();
            forecastCondition.displayTime = i + ":00 PM";
            forecastCondition.tempFahrenheit = i*new Random().nextInt(100);
            add(forecastCondition);
        }
        adapter = new ForecastGridAdapter(getInstrumentation().getContext(), array, true);
    }

    /**
     * Add a ForecastCondition to the array
     * @param condition
     */
    private void add(ForecastCondition condition) {
        array[index++] = condition;
    }

    @UiThreadTest
    public void testGetView() throws Exception {
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(0, null, null);
            ForecastGridAdapter.ViewHolder holder = (ForecastGridAdapter.ViewHolder) view.getTag();
            assertNotNull("No ViewHolder attached to the view!", holder);
            assertNotNull(holder.icon);
            assertNotNull(holder.temperature);
            assertNotNull(holder.time);
        }
    }
}
