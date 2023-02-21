public class Converter {
    // attribute
    private double input;

    // constructor
    public Converter() {
        this.input = Double.NaN;
    }

    // Overloaded constructor
    public Converter(double input) {
        this.input = input;
    }

    // Getter
    public double getInput() {
        return input;
    }

    // Setter
    public void setInput(double input) {
        this.input = input;
    }

    /**
     * Convert method
     * Subclasses will override this method to provide specific conversion functionality.
     * @return the converted value, or the original input if not converted
     */
    public double convert() {
        return input;
    }

}
