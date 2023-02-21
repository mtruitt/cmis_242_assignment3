public class DistanceConverter extends Converter {

    // Constructor
    public DistanceConverter() {
        super();
    }

    // Overloaded Constructor
    public DistanceConverter(double input) {
        super(input);
    }

    /**
     * Converts the input distance in miles to kilometers
     * Formula: KM = M * 1.609.
     * @return the converted distance in kilometers, or Double.NaN if no input value is set
     */
    @Override
    public double convert() {
        if (Double.isNaN(getInput())) {
            return Double.NaN;
        }
        return getInput() * 1.609;
    }
}