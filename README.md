# RestCountries Sorter

This project is a Maven-based application for sorting countries based on population density and finding the country in Asia with the most borders.

## Instructions

### Prerequisites
- OpenJDK 21 or later installed on your system
- Maven installed on your system

### Clone the Repository
```bash
git clone https://github.com/SebastianoMB/restcountries-sorter.git
```

### Navigate to the Project Directory
```bash
cd restcountries-sorter
```

### Build the Project
```bash
mvn clean package
```

### Run the Application
```bash
java -jar target/restcountries-sorter-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```

The application will fetch data from the RestCountries API, sort the countries by population density, 
find the Asian country with the most neighbouring countries, and display the result.


### Additional Notes
- Ensure that you have an internet connection to fetch data from the RestCountries API.
- The application uses OpenJDK 21, so make sure it is properly configured on your system.
