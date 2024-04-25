package restcountries.sorter.impl.model;

public record Country(
        String name,
        long area,
        long population,
        double populationDensity,
        String continent,
        int numberBorderingCountries
) {
    @Override
    public String toString() {
        return String.format("Country: %s%n" +
                        "  - Area: %d sq. km%n" +
                        "  - Population: %d%n" +
                        "  - Population Density: %.2f people per sq. km%n" +
                        "  - Continent: %s%n" +
                        "  - Number of Bordering Countries: %d%n",
                name(), area(), population(), populationDensity(), continent(), numberBorderingCountries());
    }
}
