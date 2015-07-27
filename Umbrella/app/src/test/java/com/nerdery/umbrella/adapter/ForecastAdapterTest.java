package com.nerdery.umbrella.adapter;

import com.nerdery.umbrella.model.ForecastCondition;

import junit.framework.TestCase;

/**
 * Tests for {@link ForecastAdapter}
 *
 * @author Phil Brown
 * @since 11:31 AM Jul 24, 2015
 */
public class ForecastAdapterTest extends TestCase {

    /**
     * Test the {@link ForecastAdapter#resizeArray(ForecastCondition[])} method.
     * @throws Exception
     */
    public void testResizeArray() throws Exception {
        ForecastCondition[] array = new ForecastCondition[24];
        for (int i = 9; i < 24; i++) {
            array[i] = new ForecastCondition();
        }
        ForecastCondition[] shrunk = ForecastAdapter.resizeArray(array);
        assertEquals("Resized to an incorrect length", 15, shrunk.length);
    }
}
