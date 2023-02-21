public class TemperatureConverter extends Converter {

    // Constructor
    public TemperatureConverter() {
        super();
    }

    // Overloaded Constructor
    public TemperatureConverter(double input) {
        super(input);
    }

    /**
     * Converts the input Fahrenheit temperature to Celsius.
     * Formula: °C = 5/9(°F – 32) or °C = .5556(°F – 32)
     * @return the converted temperature in Celsius, or Double.NaN if no input value is set
     */
    @Override
    public double convert() {
        if(Double.isNaN(getInput())) {
            return Double.NaN;
        }
        return ((getInput() - 32) * 5) / 9;
    }
}