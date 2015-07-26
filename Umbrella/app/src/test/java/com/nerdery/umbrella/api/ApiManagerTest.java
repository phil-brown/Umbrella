package com.nerdery.umbrella.api;


import junit.framework.TestCase;

/**
 * Tests Api Manager methods
 *
 * @author Phil Brown
 * @since 9:16 AM Jul 26, 2015
 */
public class ApiManagerTest extends TestCase {

    public void testConstructor() throws Exception {
        ApiManager apiManager = new ApiManager();
        assertNotNull(apiManager);
    }

    public void testWeatherApi() throws Exception {
        assertNotNull(ApiManager.getWeatherApi());
    }

    public void testIconApi() throws Exception {
        assertNotNull(ApiManager.getIconApi());
    }
}
