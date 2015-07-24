package com.nerdery.umbrella;

import android.cts.util.PollingCheck;
import android.test.InstrumentationTestCase;

import com.nerdery.umbrella.api.WeatherApi;

/**
 * Helper class for API Tests
 *
 * @author Phil Brown
 * @since 8:05 AM Jul 24, 2015
 */
public class ApiTest extends InstrumentationTestCase {

    /**
     * Maximum amount of time a task should take. Fail the test if the task takes longer. This is a quality metric.
     */
    public static final long TASK_BENCHMARK = 10000;//10 seconds
    /**
     * Provides access to the API
     */
    protected WeatherApi mWeatherApi;

    /**
     * Used to block tests from completing while asynchronous tasks are being done
     */
    protected boolean hasCompletedRequest;

    /**
     * Times out if the request takes longer than {@link #TASK_BENCHMARK}
     */
    private PollingCheck mPollingCheck = new PollingCheck(TASK_BENCHMARK) {
        @Override
        protected boolean check() {
            return hasCompletedRequest;
        }
    };

    /**
     * Call at the start of each method to properly reset test values.
     */
    protected void reset() {
        hasCompletedRequest = false;
    }

    /**
     * Get the PollingCheck to use to ensure the requests are faster than the {@link #TASK_BENCHMARK}
     * @return
     */
    protected PollingCheck getPollingCheck() {
        return mPollingCheck;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        hasCompletedRequest = false;
    }
}
