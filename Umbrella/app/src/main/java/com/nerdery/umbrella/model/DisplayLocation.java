package com.nerdery.umbrella.model;

/**
 * Represents a "display_location" returned from Weather Underground
 *
 * Does not include all available only data- only potentially useful fields are included
 *
 * @author bherbst
 */
public class DisplayLocation {
    public String full;
    public String city;
    public String state;
    public String state_name;
    public String country;
    public String zip;

    /**
     * Get the location name to show to the user.
     * @return a formatted string displaying the user's location.
     */
    public String getDisplayName() {
        if (city != null) {
            if (state != null) {
                return city + ", " + state;
            }
            if (state_name != null) {
                return city + ", " + state_name;
            }
            return city;
        }
        else if (zip != null) {
            return zip;
        }
        return full;//whatever is available
    }
}
