package com.example.mitwong.migrainetracker;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitwong on 6/13/2015.
 */
public class YahooClient {

    public static String YAHOO_GEO_URL = "http://where.yahooapis.com/v1";
    public static String YAHOO_WEATHER_URL = "http://weather.yahooapis.com/forecastrss";

    // Need to get an api key
    private static String APPID = "";

    public static List<CityResult> getCityList(String cityName) {
        // Will store city results from API call
        List<CityResult> result = new ArrayList<>();
        HttpURLConnection urlConnection = null;

        try {
            // Create a connection to the Yahoo Weather API and pull and parse XML
            String query = makeQueryCityURL(cityName);
            URL url = new URL(query);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(new InputStreamReader(urlConnection.getInputStream()));

            int event = parser.getEventType();

            CityResult cityResult = null;
            String tagName = null;
            String currentTag = null;

            // Begin parsing XML
            while (event != XmlPullParser.END_DOCUMENT) {
                tagName = parser.getName();

                if (event == XmlPullParser.START_TAG) {
                    if (tagName.equals("place")) {
                        cityResult = new CityResult();
                        Log.d("XMLPull", "New City Found");
                    }

                    currentTag = tagName;
                    Log.d("XMLPull", "Tag [" + tagName + "]");
                } else if (event == XmlPullParser.TEXT){
                    // Process text
                    // TODO: Need to refactor this into constant strings
                    String parserText = parser.getText();
                    if ("woeid".equals(currentTag)) {
                        cityResult.setWoeid(parserText);
                    } else if ("name".equals(currentTag)) {
                        cityResult.setCityName(parserText);
                    } else if ("country".equals(currentTag)) {
                        cityResult.setCountry(parserText);
                    }
                    // Add other else ifs to handle other tags
                } else if (event == XmlPullParser.END_TAG) {
                    if ("place".equals(tagName)) {
                        result.add(cityResult);
                    }
                }
                event = parser.next();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        finally {
            try {
                urlConnection.disconnect();
            } catch (Throwable e){
                e.printStackTrace();
            }
        }

        return result;
    }

    // Change 5 to a config number
    private static String makeQueryCityURL(String cityName) {
        // We remove spaces in cityName
        cityName = cityName.replaceAll(" ", "%20");
        return YAHOO_GEO_URL + "/places.q(" + cityName + "%2A);count=" + 5 + "?appid=" + APPID;
    }

}
