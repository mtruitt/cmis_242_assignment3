/**
 * Mark Truitt
 * CMIS 242 - Assignment 3: GUI & Polymorphism
 * 2023/02/20
 *
 * The main class for the application. It creates a window that allows the user to convert distances or temperatures between metric and imperial units.
 *
 * Utilized DRY to reduce the repeated code for things like messages, input, results, buttons, and components for panels.
 */
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUIConverter {

    /**
     * Displays an error message dialog with the specified message.
     *
     * @param message the message to display in the error dialog
     */
    private void showErrorMessage(String message) {
        // Display a message dialog with the specified message and an error icon
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an input dialog with the specified message and returns the user's input as a String.
     *
     * @param message the message to display in the input dialog
     * @return the user's input as a String, or null if the user cancels or closes the dialog
     */
    private String getUserInput(String message) {
        // Display an input dialog with the specified message and return the user's input
        return JOptionPane.showInputDialog(null, message);
    }

    /**
     * Displays a message dialog with the input value, converted value, and units of measurement.
     *
     * @param inputValue     the original input value
     * @param convertedValue the converted value
     * @param inputUnit      the unit of measurement for the input value
     * @param convertedUnit  the unit of measurement for the converted value
     */
    private void displayConversionResult(double inputValue, double convertedValue, String inputUnit, String convertedUnit) {
        // Create a formatted message string with the input value, converted value, and units of measurement
        String message = String.format("%.2f %s = %.2f %s", inputValue, inputUnit, convertedValue, convertedUnit);
        // Display a message dialog with the formatted message
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Creates and configures a new JButton instance with the specified label, size, and action listener.
     *
     * @param label          the label to display on the button
     * @param size           the preferred size of the button
     * @param actionListener the action listener to associate with the button
     * @return a new JButton instance with the specified properties
     */
    private JButton createButton(String label, Dimension size, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.setPreferredSize(size);
        button.addActionListener(actionListener);
        return button;
    }

    /**
     * Adds an array of components to the specified panel using the specified layout manager.
     *
     * @param panel      the panel to add components to
     * @param layout     the layout manager to use for the panel
     * @param components an array of components to add to the panel
     */
    private void addComponents(JPanel panel, LayoutManager layout, Component... components) {
        // Set the layout manager of the panel to the specified layout
        panel.setLayout(layout);
        // Iterate over the array of components and add each one to the panel
        for (Component component : components) {
            panel.add(component);
        }
    }

    public GUIConverter() {

        JFrame frame = new JFrame("Converter"); // create a new JFrame window with the title "Converter"
        JPanel panel = new JPanel(new FlowLayout()); // create a new JPanel with a FlowLayout manager

        /**
         * The button used to convert temperatures. When clicked, this button prompts the user to enter a Fahrenheit temperature,
         * converts the temperature to Celsius, and displays the result to the user. If the user enters an invalid input, an error message is displayed.
         */
        JButton tempButton = createButton("Temperature Converter", new Dimension(200, 50), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = getUserInput("Enter Fahrenheit temperature:"); // Prompt the user to enter a Fahrenheit temperature
                if (input == null) {
                    return; // User clicked "Cancel" button, do nothing
                }
                try {
                    double fTemp = Double.parseDouble(input); // Convert the user's input to a double
                    TemperatureConverter converter = new TemperatureConverter(fTemp); // Create a new TemperatureConverter object with the user's input as the input value
                    double cTemp = converter.convert(); // Convert the temperature from Fahrenheit to Celsius
                    displayConversionResult(fTemp, cTemp, "Fahrenheit", "Celsius"); // Display the converted temperature
                } catch (NumberFormatException ex) { // Handle any exceptions that occur
                    showErrorMessage("Invalid input: Please input a valid temperature to convert."); // Display an error message
                }
            }
        });

        /**
         * The button used to convert distances. When clicked, this button prompts the user to enter a distance in miles,
         * converts the distance to kilometers, and displays the result to the user. If the user enters an invalid input,
         * an error message is displayed.
         */
        JButton distButton = createButton("Distance Converter", new Dimension(200, 50), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = getUserInput("Enter distance in miles:"); // Prompt the user to enter miles
                if (input == null) {
                    return; // User clicked "Cancel" button, do nothing
                }
                try {
                    double miles = Double.parseDouble(input); // Convert the user's input to a double
                    DistanceConverter converter = new DistanceConverter(miles); // Create a new DistanceConverter object with the user's input as the input value
                    double km = converter.convert(); // Convert the distance from Miles to Kilometers
                    displayConversionResult(miles, km, "miles", "kilometers"); // Display the converted distance
                } catch (NumberFormatException ex) { // Handle any exceptions that occur
                    showErrorMessage("Invalid input: Please input miles to convert to kilometers."); // Display an error message
                }
            }
        });

        /**
         * The button used to exit the application. When clicked, this button terminates the application.
         */
        JButton exitButton = createButton("Exit", new Dimension(405, 40), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.addComponents(panel, new FlowLayout(), tempButton, distButton, exitButton);

        frame.add(panel); // Add panel to frame
        frame.setSize(430, 150); // Set the size of the frame
        frame.setLocationRelativeTo(null); // Center the frame on the monitor
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit when user closes window
        frame.setVisible(true); /// Display the frame
    }

    /**
     * The main entry point of the application.
     */
    public static void main(String[] args) {
        // Create a new GUIConverter instance, which launches the GUI
        new GUIConverter();
    }
}