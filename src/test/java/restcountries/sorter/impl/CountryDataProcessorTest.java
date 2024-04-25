package restcountries.sorter.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restcountries.sorter.impl.model.Country;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CountryDataProcessorTest {

    private static String jsonStringTest = null;

    @BeforeAll
    static void setup() {
        jsonStringTest = loadJSONFromFile();
    }

    @Test
    @DisplayName("Test fetching country population data from API")
    void testFetchCountryPopulationDataFromAPI() {
        String jsonString = CountryDataProcessor.fetchCountryPopulationDataFromAPI();
        Assertions.assertNotNull(jsonString);
    }

    @Test
    @DisplayName("Test parsing countries from JSON string")
    void testParseCountries() {
        List<Country> countries = CountryDataProcessor.parseCountries(jsonStringTest);
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
    }

    @Test
    @DisplayName("Test getting country in Asia with most borders")
    void testGetCountryInAsiaWithMostBorders() {
        List<Country> countries = CountryDataProcessor.parseCountries(jsonStringTest);
        Country country = CountryDataProcessor.getCountryInAsiaWithMostBorders(countries);
        Assertions.assertEquals("Yemen", country.name());
    }

    @Test
    @DisplayName("Test sorting countries by population density")
    void testSortCountriesByPopulationDensity() {
        List<Country> countries = CountryDataProcessor.parseCountries(jsonStringTest);
        List<Country> sortedCountries = CountryDataProcessor.sortCountriesByPopulationDensity(countries);
        Assertions.assertEquals("Mayotte", sortedCountries.get(0).name());
    }

    /**
     * Load JSON data from a test.json file stored in <i>src/test/resources/</i>.
     *
     * @return JSON {@link Country} data as a string
     */
    private static String loadJSONFromFile() {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/test.json"));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
