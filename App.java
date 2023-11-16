import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
    public static void main(String[] args) throws IOException {
        // Hardcoded values for num1, num2, and operation
        double num1 = 10.5;
        double num2 = 5.2;
        String operation = "+";

        // Perform the calculation based on the chosen operation
        double result = calculate(num1, num2, operation);

        // Display the result
        System.out.println("Result: " + result);

        // Fetch and display a fact about the result using the Numbers API
        displayNumberFact((int) result);
    }

    private static double calculate(double num1, double num2, String operation) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    System.out.println("Cannot divide by zero. Please enter a non-zero second number.");
                    System.exit(1);
                }
            default:
                System.out.println("Invalid operation. Please enter +, -, *, or /.");
                System.exit(1);
                return 0; // This line is not reachable, but required for compilation
        }
    }

    private static void displayNumberFact(int number) {
        try {
            // Connect to the Numbers API and fetch a fact
            URL apiUrl = new URL("http://numbersapi.com/" + number + "?json");
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Read and display the fact
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String fact = reader.readLine();
                System.out.println("Number Fact: " + fact);
                reader.close();
            } else {
                System.out.println("Failed to fetch number fact. HTTP response code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
