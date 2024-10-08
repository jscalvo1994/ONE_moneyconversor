import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia del conversor de monedas
        CurrencyConverter currencyConverter = new CurrencyConverter();

        try {
            // Iniciar el proceso del conversor de monedas
            currencyConverter.start();
        } catch (IOException e) {
            // Manejar cualquier excepción de E/S que pueda ocurrir
            System.err.println("Error al obtener las tasas de conversión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
