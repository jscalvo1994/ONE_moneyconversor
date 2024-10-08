import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    private Map<String, Double> conversionRates;

    public void start() throws IOException {
        // Obtener las tasas de cambio de la API
        fetchConversionRates();

        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al Conversor de Monedas!");
        System.out.println("Este programa le permitirá convertir un monto de una moneda a otra usando tasas de cambio en tiempo real.");

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1) Ingresar un monto para convertir");
            System.out.println("2) Cerrar el programa");
            String option = scanner.nextLine();

            if (option.equals("1")) {
                double amount = requestAmount(scanner);
                String baseCurrency = selectCurrency(scanner, "Seleccione la moneda base del monto ingresado:");

                // Confirmar monto y moneda
                if (!confirmSelection(scanner, "Monto: " + amount + ", Moneda: " + baseCurrency)) {
                    continue;
                }

                String targetCurrency = selectCurrency(scanner, "Seleccione la moneda a la que desea convertir:");

                // Confirmar monto y moneda a convertir
                if (!confirmSelection(scanner, "Convertir " + amount + " " + baseCurrency + " a " + targetCurrency)) {
                    continue;
                }

                // Realizar la conversión
                double convertedAmount = convertCurrency(amount, baseCurrency, targetCurrency);
                System.out.printf("Resultado: %.2f %s equivale a %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
            } else if (option.equals("2")) {
                System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta luego!");
                break;
            } else {
                System.out.println("Opción no válida, por favor ingrese una opción numérica válida.");
            }
        }
    }

    private void fetchConversionRates() throws IOException {
        // URL de la API con tu clave de API
        String apiKey = "6fb598e3d03f586077511bf5";  // Coloca aquí tu clave
        String urlString = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD"; // Aquí selecciono USD como moneda base

        // Realizar la solicitud HTTP
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Leer la respuesta
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        // Cerrar conexiones
        in.close();
        connection.disconnect();

        // Convertir la respuesta JSON a un objeto Map usando Gson
        JsonObject jsonResponse = JsonParser.parseString(content.toString()).getAsJsonObject();
        JsonObject conversionRatesJson = jsonResponse.getAsJsonObject("conversion_rates");

        // Deserializar el JSON en un Map<String, Double> usando Gson
        Gson gson = new Gson();
        conversionRates = gson.fromJson(conversionRatesJson, new TypeToken<Map<String, Double>>(){}.getType());

        System.out.println("Tasas de conversión obtenidas exitosamente.");
    }

    private double requestAmount(Scanner scanner) {
        while (true) {
            System.out.println("Por favor, ingrese el monto a convertir (solo números):");
            String input = scanner.nextLine();

            try {
                double amount = Double.parseDouble(input);
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("El monto ingresado no es válido. ¿Qué desea hacer?");
                System.out.println("1) Corregir monto");
                System.out.println("2) Cerrar el programa");

                String option = scanner.nextLine();
                if (option.equals("2")) {
                    System.exit(0); // Cierra el programa
                }
            }
        }
    }

    private String selectCurrency(Scanner scanner, String prompt) {
        System.out.println(prompt);
        listCurrencies();

        while (true) {
            String input = scanner.nextLine();
            if (conversionRates.containsKey(input.toUpperCase())) {
                return input.toUpperCase();
            } else {
                System.out.println("Moneda no válida, por favor seleccione una moneda de la lista usando su código de tres letras (por ejemplo, USD).");
            }
        }
    }

    private boolean confirmSelection(Scanner scanner, String message) {
        System.out.println("¿Es correcta la selección? " + message);
        System.out.println("1) Corregir monto");
        System.out.println("2) Corregir moneda");
        System.out.println("3) Todo es correcto");

        while (true) {
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    return false; // Corregir monto
                case "2":
                    return false; // Corregir moneda
                case "3":
                    return true; // Todo es correcto
                default:
                    System.out.println("Opción no válida, por favor ingrese una opción numérica válida.");
            }
        }
    }

    private void listCurrencies() {
        System.out.println("Monedas disponibles:");
        for (String currency : conversionRates.keySet()) {
            System.out.println(currency);
        }
    }

    private double convertCurrency(double amount, String baseCurrency, String targetCurrency) {
        double baseRate = conversionRates.get(baseCurrency);
        double targetRate = conversionRates.get(targetCurrency);
        return amount * (targetRate / baseRate);
    }
}
