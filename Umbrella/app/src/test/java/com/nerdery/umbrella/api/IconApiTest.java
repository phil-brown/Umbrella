package com.nerdery.umbrella.api;

import junit.framework.TestCase;

/**
 * Unit Test for the {@link IconApi} class.
 *
 * @author Phil Brown
 * @since 8:19 AM Jul 24, 2015
 */
public class IconApiTest extends TestCase {

    /**
     * The Icon API
     */
    private IconApi mIconApi;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mIconApi = ApiManager.getIconApi();
    }

    @Override
    public void tearDown() throws Exception {
        mIconApi = null;
        super.tearDown();
    }

    /**
     * Tests that the {@link IconApi#getUrlForIcon(String, boolean)} method is returning the expected values
     * @throws Exception
     */
    public void testGetUrlForIcon() throws Exception {
        String url = mIconApi.getUrlForIcon("clear", true);
        assertEquals("http://nerdery-umbrella.s3.amazonaws.com/clear-selected.png", url);
        url = mIconApi.getUrlForIcon("clear", false);
        assertEquals("http://nerdery-umbrella.s3.amazonaws.com/clear.png", url);
        url = mIconApi.getUrlForIcon(null, false);
        assertEquals("http://nerdery-umbrella.s3.amazonaws.com/null.png", url);
    }
}
