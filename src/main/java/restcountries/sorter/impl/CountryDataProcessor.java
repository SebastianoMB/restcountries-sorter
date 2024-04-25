package restcountries.sorter.impl;

import com.google.gson.*;
import restcountries.sorter.impl.model.Country;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountryDataProcessor  {

    /**
     * Fetches {@link Country} population data from the API.
     *
     * @return A string containing the response body from the API or null if an error occurs
     */
    public static String fetchCountryPopulationDataFromAPI() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            // Filtering API request by name, population, area, continents, borders
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://restcountries.com/v3.1/all?fields=name,population,area,continents,borders"))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            e.printStackTrace(); // TODO: Improve error handling by adding a logging library
            return null;
        }
    }

    /**
     * Finds the {@link Country} in Asia with the most borders.
     *
     * @param countries A list of countries to search from
     * @return The {@link Country} in Asia with the most borders
     */
    public static Country getCountryInAsiaWithMostBorders(List<Country> countries) {
        Country countryWithMostBorders = null;
        int maxBorders = 0;

        for(Country country : countries) {
            if(country.continent().equals("Asia")){
                int numBorders = country.numberBorderingCountries();

                if(numBorders > maxBorders) {
                    maxBorders = numBorders;
                    countryWithMostBorders = country;
                }
            }
        }
        return countryWithMostBorders;
    }

    /**
     * Parses JSON data representing countries into a list of {@link Country} objects.
     *
     * @param jsonString The JSON data representing countries
     * @return A list of {@link Country} objects parsed from the JSON data
     */
    public static List<Country> parseCountries(String jsonString) {
        List<Country> countries = new ArrayList<>();

        JsonElement jsonElement = JsonParser.parseString(jsonString);

        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            String name = jsonObject.getAsJsonObject("name").get("common").getAsString();
            long area = jsonObject.get("area").getAsLong();
            long population = jsonObject.get("population").getAsLong();
            JsonArray continentsArray = jsonObject.getAsJsonArray("continents");
            String continent = continentsArray.get(0).getAsString();

            // Calculate the number of bordering continents
            int numBorderingContinents = jsonObject.getAsJsonArray("borders").size();

            double populationDensity = population / (double) area;

            countries.add(new Country(name, area, population, populationDensity, continent, numBorderingContinents));
        }

        return countries;
    }

    /**
     * Sorts a list of {@link Country} by population density in descending order.
     * Entries with population density equal to infinity are removed before sorting e.g (Vatican City).
     *
     * @param countries The list of {@link Country} to be sorted
     * @return The sorted list of {@link Country} by population density
     */
    public static List<Country> sortCountriesByPopulationDensity(List<Country> countries) {
        List<Country> filteredCountries = countries.stream()
                .filter(country -> Double.isFinite(country.populationDensity()))
                .collect(Collectors.toList());

        filteredCountries.sort((c1, c2) -> Double.compare(c2.populationDensity(), c1.populationDensity()));
        return filteredCountries;
    }
}
