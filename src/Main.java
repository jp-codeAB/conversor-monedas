
import service.CurrencyService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // creación de objetos
        Scanner sc = new Scanner(System.in);
        CurrencyService service = new CurrencyService();
        int option = 0;

        // Menu de opciones
        String menu = """
                ***************************************************
                      Sea bienvenid@ al Conversor de Monedas
                
                1) Dólar ->> Peso argentino
                2) Peso argentino ->> Dólar
                3) Dólar ->> Real brasileño
                4) Real brasileño ->> Dólar
                5) Dólar =>> Peso Colombiano❤
                6) Peso Colombiano❤ ->> Dólar
                7) Dólar ->> Euro
                8) Euro ->> Dólar
                9) Salir
                Elija una opción válida:
                ***************************************************
                """;

        while (option != 9) {
            System.out.print(menu);
            try {
                option = Integer.parseInt(sc.nextLine());
                if (option == 9) break;

                System.out.print("Ingrese el valor que desea convertir: ");
                double amount = Double.parseDouble(sc.nextLine());

                //Casos disponibles para conversion
                // Añadí una conversión de moneda más de Euro a Dólar y viceversa
                switch (option) {
                    case 1 -> service.executeConversion("USD", "ARS", amount);
                    case 2 -> service.executeConversion("ARS", "USD", amount);
                    case 3 -> service.executeConversion("USD", "BRL", amount);
                    case 4 -> service.executeConversion("BRL", "USD", amount);
                    case 5 -> service.executeConversion("USD", "COP", amount);
                    case 6 -> service.executeConversion("COP", "USD", amount);
                    case 7 -> service.executeConversion("USD", "EUR", amount);
                    case 8 -> service.executeConversion("EUR", "USD", amount);
                    default -> System.out.println("Opción no válida.");
                }
            //Manejo de error para un dato diferente de tipo numerico
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        System.out.println("Programa finalizado. ¡Gracias!");
    }
}