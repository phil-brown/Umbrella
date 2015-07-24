package com.nerdery.umbrella.adapter;

import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;

import com.nerdery.umbrella.model.ForecastCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for {@link ForecastAdapter}
 *
 * @author Phil Brown
 * @since 11:31 AM Jul 24, 2015
 */
public class ForecastAdapterTest extends InstrumentationTestCase {

    @UiThreadTest
    public void testConstructor() throws Exception {
        List<ForecastCondition> list = new ArrayList<>(30);
        ForecastCondition forecastCondition;
        for (int i = 6; i < 12; i++) {
            forecastCondition = new ForecastCondition();
            forecastCondition.displayTime = i + ":00 PM";
            list.add(forecastCondition);
        }
        forecastCondition = new ForecastCondition();
        forecastCondition.displayTime = "12:00 AM";
        list.add(forecastCondition);

        for (int i = 1; i < 12; i++) {
            forecastCondition = new ForecastCondition();
            forecastCondition.displayTime = i + ":00 AM";
            list.add(forecastCondition);
        }
        forecastCondition = new ForecastCondition();
        forecastCondition.displayTime = "12:00 PM";
        list.add(forecastCondition);

        for (int i = 1; i < 12; i++) {
            forecastCondition = new ForecastCondition();
            forecastCondition.displayTime = i + ":00 PM";
            list.add(forecastCondition);
        }

        ForecastAdapter adapter = new ForecastAdapter(getInstrumentation().getContext(), list);
        ForecastCondition[] today = adapter.getToday();
        assertNotNull(today);
        assertEquals("unexpected number of forecasts", 6, today.length);
        ForecastCondition[] tomorrow = adapter.getTomorrow();
        assertNotNull(tomorrow);
        assertEquals("unexpected number of forecasts", 24, tomorrow.length);

    }
}
