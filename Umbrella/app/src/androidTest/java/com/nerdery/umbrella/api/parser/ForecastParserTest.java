package com.nerdery.umbrella.api.parser;

import android.test.InstrumentationTestCase;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nerdery.umbrella.model.ForecastCondition;

/**
 * Tests the {@link ForecastParser}
 *
 * @author Phil Brown
 * @since 7:31 AM Jul 24, 2015
 */
public class ForecastParserTest extends InstrumentationTestCase {

    String forecast = "{\n" +
            "        \"FCTTIME\": {\n" +
            "            \"hour\": \"20\",\n" +
            "            \"hour_padded\": \"20\",\n" +
            "            \"min\": \"00\",\n" +
            "            \"min_unpadded\": \"0\",\n" +
            "            \"sec\": \"0\",\n" +
            "            \"year\": \"2015\",\n" +
            "            \"mon\": \"7\",\n" +
            "            \"mon_padded\": \"07\",\n" +
            "            \"mon_abbrev\": \"Jul\",\n" +
            "            \"mday\": \"27\",\n" +
            "            \"mday_padded\": \"27\",\n" +
            "            \"yday\": \"207\",\n" +
            "            \"isdst\": \"1\",\n" +
            "            \"epoch\": \"1438045200\",\n" +
            "            \"pretty\": \"8:00 PM CDT on July 27, 2015\",\n" +
            "            \"civil\": \"8:00 PM\",\n" +
            "            \"month_name\": \"July\",\n" +
            "            \"month_name_abbrev\": \"Jul\",\n" +
            "            \"weekday_name\": \"Monday\",\n" +
            "            \"weekday_name_night\": \"Monday Night\",\n" +
            "            \"weekday_name_abbrev\": \"Mon\",\n" +
            "            \"weekday_name_unlang\": \"Monday\",\n" +
            "            \"weekday_name_night_unlang\": \"Monday Night\",\n" +
            "            \"ampm\": \"PM\",\n" +
            "            \"tz\": \"\",\n" +
            "            \"age\": \"\",\n" +
            "            \"UTCDATE\": \"\"\n" +
            "        },\n" +
            "        \"temp\": {\n" +
            "            \"english\": \"82\",\n" +
            "            \"metric\": \"28\"\n" +
            "        },\n" +
            "        \"dewpoint\": {\n" +
            "            \"english\": \"68\",\n" +
            "            \"metric\": \"20\"\n" +
            "        },\n" +
            "        \"condition\": \"Chance of a Thunderstorm\",\n" +
            "        \"icon\": \"chancetstorms\",\n" +
            "        \"icon_url\": \"http://icons.wxug.com/i/c/k/chancetstorms.gif\",\n" +
            "        \"fctcode\": \"14\",\n" +
            "        \"sky\": \"50\",\n" +
            "        \"wspd\": {\n" +
            "            \"english\": \"13\",\n" +
            "            \"metric\": \"21\"\n" +
            "        },\n" +
            "        \"wdir\": {\n" +
            "            \"dir\": \"SSE\",\n" +
            "            \"degrees\": \"163\"\n" +
            "        },\n" +
            "        \"wx\": \"Scattered Thunderstorms\",\n" +
            "        \"uvi\": \"0\",\n" +
            "        \"humidity\": \"61\",\n" +
            "        \"windchill\": {\n" +
            "            \"english\": \"-9999\",\n" +
            "            \"metric\": \"-9999\"\n" +
            "        },\n" +
            "        \"heatindex\": {\n" +
            "            \"english\": \"85\",\n" +
            "            \"metric\": \"30\"\n" +
            "        },\n" +
            "        \"feelslike\": {\n" +
            "            \"english\": \"85\",\n" +
            "            \"metric\": \"30\"\n" +
            "        },\n" +
            "        \"qpf\": {\n" +
            "            \"english\": \"0.02\",\n" +
            "            \"metric\": \"1\"\n" +
            "        },\n" +
            "        \"snow\": {\n" +
            "            \"english\": \"0.0\",\n" +
            "            \"metric\": \"0\"\n" +
            "        },\n" +
            "        \"pop\": \"39\",\n" +
            "        \"mslp\": {\n" +
            "            \"english\": \"29.72\",\n" +
            "            \"metric\": \"1006\"\n" +
            "        }\n" +
            "    }";

    public void testDeserialize() throws Exception {
        ForecastParser parser = new ForecastParser();
        JsonObject json = (JsonObject) new JsonParser().parse(forecast);
        ForecastCondition condition = parser.deserialize(json, null, null);
        assertNotNull(condition);
        assertNotNull(condition.condition);
        assertNotNull(condition.displayTime);
        assertNotNull(condition.icon);
        assertNotNull(condition.tempCelsius);
        assertNotNull(condition.tempFahrenheit);
        assertNotNull(condition.time);

    }
}
