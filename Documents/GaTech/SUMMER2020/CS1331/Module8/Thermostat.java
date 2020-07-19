import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * A class for a thermostat GUI in JavaFX.
 *@author hconner6
 *@version 1
 */

public class Thermostat extends Application {
    private static double temp = 70;
    private static String units = "F";

    /**
     * This is the main method
     * @param args A string array of what is passed into the main method.
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static void incrementTemp() {
        Thermostat.setTemp((Thermostat.getTemp() + 1));
    }
    private static void decrementTemp() {
        Thermostat.setTemp((Thermostat.getTemp() - 1));
    }

    private static void convertTemp() {
        if (Thermostat.getUnits().equals("F")) {
            Thermostat.setTemp((Thermostat.getTemp() - 32) * 5 / 9);
            Thermostat.setTemp(Math.round(Thermostat.getTemp()));
            Thermostat.setUnits("C");
        } else if (Thermostat.getUnits().equals("C")) {
            Thermostat.setTemp((Thermostat.getTemp() * 9 / 5) + 32);
            Thermostat.setTemp(Math.round(Thermostat.getTemp()));
            Thermostat.setUnits("F");
        }
    }
    /**
     * A getter for the temperature.
     * @return Returns a double representing the temperature.
     */
    public static double getTemp() {
        return temp;
    }
    /**
     * A getter for the units.
     * @return Returns a String of the units.
     */
    public static String getUnits() {
        return units;
    }
    /**
     * A setter for the temperature.
     * @param newTemp The new temperature.
     */
    public static void setTemp(double newTemp) {
        temp = newTemp;
    }
    /**
     * A setter for the units.
     * @param newUnits The new units.
     */
    public static void setUnits(String newUnits) {
        units = newUnits;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Thermostat");
        Button increaseButton = new Button("Increase");
        Button decreaseButton = new Button("Decrease");
        Button convertButton = new Button("Convert");
        Button coolButton = new Button("Cool");
        Button heatButton = new Button("Heat");
        Button menuButton = new Button("Menu");
        Button timeButton = new Button("Time");
        Button weatherButton = new Button("Weather");
        Button backButton = new Button("Back");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(increaseButton, 0, 5);
        grid.add(decreaseButton, 1, 5);
        grid.add(convertButton, 2, 5);
        grid.add(coolButton, 0, 8);
        grid.add(heatButton, 1, 8);
        grid.add(menuButton, 0, 15);
        grid.add(timeButton, 1, 15);
        grid.add(weatherButton, 2, 15);
        grid.add(backButton, 3, 15);
        timeButton.setVisible(false);
        weatherButton.setVisible(false);
        backButton.setVisible(false);

        final TextField temperatureTextField = new TextField(temp + "\u00B0" + units);
        temperatureTextField.setEditable(false);
        grid.add(temperatureTextField, 0, 0);

        final Text coolOrHeatMessage = new Text();
        grid.add(coolOrHeatMessage, 1, 0);
        final Text timeOrWeatherMessage = new Text();
        grid.add(timeOrWeatherMessage, 1, 12);


        increaseButton.setOnAction(event -> {
                Thermostat.incrementTemp();
                temperatureTextField.setText(temp + "\u00B0" + units);
            });

        decreaseButton.setOnAction(event -> {
                Thermostat.decrementTemp();
                temperatureTextField.setText(temp + "\u00B0" + units);
            });

        convertButton.setOnAction(event -> {
                Thermostat.convertTemp();
                temperatureTextField.setText(temp + "\u00B0" + units);
            });

        coolButton.setOnAction(event -> {
                coolOrHeatMessage.setText("Cooling");
            });

        heatButton.setOnAction(event -> {
                coolOrHeatMessage.setText("Heat on");
            });

        menuButton.setOnAction(event -> {
                menuButton.setVisible(false);
                timeButton.setVisible(true);
                weatherButton.setVisible(true);
                backButton.setVisible(true);
            });

        timeButton.setOnAction(event -> {
                timeOrWeatherMessage.setText("6:04 PM");
            });

        weatherButton.setOnAction(event -> {
                timeOrWeatherMessage.setText("Sunny");
            });

        backButton.setOnAction(event -> {
                menuButton.setVisible(true);
                timeButton.setVisible(false);
                weatherButton.setVisible(false);
                backButton.setVisible(false);
                timeOrWeatherMessage.setText("");
            });

        Scene scene = new Scene(grid, 375, 300);
        primaryStage.setScene(scene);

        primaryStage.show();

    }
}