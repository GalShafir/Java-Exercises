// Weather.java

/**
 * The Weather class stores average monthly temperature data for a specific year.
 *
 * It contains an array of temperatures for 12 months and the year they correspond to.
 * This class acts as a data model for the temperature data used in the bar graph.
 */
public class Weather {
    private int year;
    private double[] temperatures; // Temperatures for 12 months

    /**
     * Constructs a Weather object with the specified year and temperatures.
     *
     * @param year         The year for which the temperature data is provided.
     * @param temperatures An array of average temperatures for each month (12 months).
     */
    public Weather(int year, double[] temperatures) {
        this.year = year;
        this.temperatures = temperatures;
    }

    /**
     * Returns the year associated with this Weather data.
     *
     * @return The year of the temperature data.
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the array of temperatures for the 12 months.
     *
     * @return An array of average monthly temperatures.
     */
    public double[] getTemperatures() {
        return temperatures;
    }
}
