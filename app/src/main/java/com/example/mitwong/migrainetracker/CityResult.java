package com.example.mitwong.migrainetracker;

/**
 * Created by mitwong on 6/6/2015.
 */
public class CityResult {
    private String woeid;
    private String cityName;
    private String country;

    public CityResult() {}

    public CityResult(String woeidIn, String cityNameIn, String countryIn) {
        this.woeid = woeidIn;
        this.cityName = cityNameIn;
        this.country = countryIn;
    }

    @Override
    public String toString() {
        return cityName + ", " + country;
    }

    // Setter Methods
    public void setWoeid(String woeidIn) {
        this.woeid = woeidIn;
    }

    public void setCityName(String cityNameIn) {
        this.cityName = cityNameIn;
    }

    public void setCountry(String countryIn) {
        this.country = countryIn;
    }
}
