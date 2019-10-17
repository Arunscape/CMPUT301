package com.example.simpleparadox.listycity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a class that keeps track of a list of city objects
 */
public class CityList {
    private List<City> cities = new ArrayList<>();

    /**
     * This adds a city to the list if the city does not exist
     *
     * @param city This is a candidate city to add
     */
    public void add(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * This returns a sorted list of cities
     *
     * @return Return the sorted list
     */
    public List<City> getCities() {
        List<City> list = cities;
        Collections.sort(list);
        return list;
    }

    /**
     * Checks if a given city exists in the cityList
     *
     * @param city
     * @return Returns true if city belongs in the list
     */
    public boolean hasCity(City city) {
        return this.cities.contains(city);
    }

    /**
     * Removes a city from the cityList
     *
     * @param city
     */
    public void delete(City city) throws Exception {
        if (hasCity(city)) {
            this.cities.remove(city);
        } else {
            throw new Exception("City does not exist in cityList!");
        }
    }

    /**
     * Gets the count of cities in cityList
     *
     * @return Returns the count of cities in the cityList
     */
    public int countCities() {
        return this.cities.size();
    }
}
