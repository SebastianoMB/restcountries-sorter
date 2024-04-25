package restcountries.sorter.api;
import restcountries.sorter.impl.CountryDataProcessor;
import restcountries.sorter.impl.model.Country;

import java.util.List;

public class CountryAnalyzer {

    public static void main(String[] args) {
            String responseBody = CountryDataProcessor.fetchCountryPopulationDataFromAPI();
            List<Country> countryList = CountryDataProcessor.parseCountries(responseBody);

            printCountryListSortedByPopulationDensity(countryList);
            printCountryWithMostBordersInAsia(countryList);
    }

    /**
     * Prints to stdout the list of {@link Country} sorted by population density.
     *
     * @param countryList The list of {@link Country}
     */
    private static void printCountryListSortedByPopulationDensity(List<Country> countryList){
        List<Country> sortedCountryList = CountryDataProcessor.sortCountriesByPopulationDensity(countryList);

        System.out.println("==============SORTED LIST================");
        for(int i = 0; i < sortedCountryList.size(); i++) {
            System.out.println("=========================================");
            System.out.println("Country number: " + i);
            System.out.println(sortedCountryList.get(i).toString());
            System.out.println("=========================================");
        }
    }

    /**
     * Prints to stdout the {@link Country} with most borders in Asia.
     *
     * @param countryList The list of {@link Country}.
     */
    private static void printCountryWithMostBordersInAsia(List<Country> countryList) {
        Country countryWithMostBordersInAsia = CountryDataProcessor.getCountryInAsiaWithMostBorders(countryList);

        System.out.println("==========MOST BORDERS IN ASIA==========");
        System.out.println(countryWithMostBordersInAsia.toString());
    }
}