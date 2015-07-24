package com.nerdery.umbrella.api;

import android.cts.util.PollingCheck;
import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.LargeTest;

import com.nerdery.umbrella.ApiTest;
import com.nerdery.umbrella.model.WeatherData;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Weather Underground API Test
 *
 * @author Phil Brown
 * @since 7:31 AM Jul 24, 2015
 */
public class WeatherApiTest extends ApiTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mWeatherApi = ApiManager.getWeatherApi();
    }

    @Override
    public void tearDown() throws Exception {
        mWeatherApi = null;
        super.tearDown();
    }

    @LargeTest
    public void testGetForecastForZipSunny() throws Exception {
        reset();
        mWeatherApi.getForecastForZip(56258, new Callback<WeatherData>() {
            @Override
            public void success(WeatherData weatherData, Response response) {
                assertNotNull(weatherData);
                assertNotNull(response);
                hasCompletedRequest = true;
            }

            @Override
            public void failure(RetrofitError error) {
                fail(error.getMessage());
                hasCompletedRequest = true;
            }
        });
        getPollingCheck().run();
    }

    @LargeTest
    public void testGetForecastForZipRainy() throws Exception {
        reset();
        mWeatherApi.getForecastForZip(Integer.MAX_VALUE, new Callback<WeatherData>() {
            @Override
            public void success(WeatherData weatherData, Response response) {
                //assertNotNull(weatherData);
                //assertNotNull(response);
                //FIXME Verify this is how Retrofit works... could be successful, but could still have an API error for some libraries.
                fail("Should not have been successful.");
                hasCompletedRequest = true;
            }

            @Override
            public void failure(RetrofitError error) {
                assertNotNull(error);
                hasCompletedRequest = true;

            }
        });
        getPollingCheck().run();
    }
}
