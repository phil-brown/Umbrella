package com.nerdery.umbrella.model;

import junit.framework.TestCase;

/**
 * Tests for the {@link DisplayLocation} Object.
 *
 * @author Phil Brown
 * @since 9:24 AM Jul 26, 2015
 */
public class DisplayLocationTest extends TestCase {

    public void testGetDisplayName() throws Exception {
        DisplayLocation displayLocation = new DisplayLocation();
        assertNull(displayLocation.getDisplayName());
        displayLocation.full = "Some Address";
        assertEquals("Some Address", displayLocation.getDisplayName());
        displayLocation.zip = "55454";
        assertEquals("55454", displayLocation.getDisplayName());
        displayLocation.city = "Minneapolis";
        displayLocation.state_name = "Minnesota";
        assertEquals("Minneapolis, Minnesota", displayLocation.getDisplayName());
        displayLocation.state = "MN";
        assertEquals("Minneapolis, MN", displayLocation.getDisplayName());
    }
}
