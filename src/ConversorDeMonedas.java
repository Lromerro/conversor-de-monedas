import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorDeMonedas {
    // Usamos una lista privada para mejor encapsulamiento
    private static final List<TasaDeCambio> registro = new ArrayList<>();

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ExchangeRateAPIClient clienteAPI = new ExchangeRateAPIClient();
        
        System.out.println("*** BIENVENIDO AL CONVERSOR DE MONEDAS. ***");
        
        String menu = """
                ----------------------------------------------
                Por favor, seleccione una opción:
                1. Dólar (USD) -> Peso Argentino (ARS)
                2. Peso Argentino (ARS) -> Dólar (USD)
                3. Dólar (USD) -> Peso Mexicano (MXN)
                4. Peso Mexicano (MXN) -> Dólar (USD)
                5. Real Brasileño (BRL) -> Peso Argentino (ARS)
                6. Peso Argentino (ARS) -> Real Brasileño (BRL)
                7. Salir
                ----------------------------------------------
                """;

        int opcion = 0;
        while (opcion != 7) {
            System.out.print(menu);
            try {
                opcion = Integer.parseInt(lectura.nextLine()); // Evita problemas con el buffer de Scanner

                switch (opcion) {
                    case 1 -> ejecutarConversion("USD", "ARS", clienteAPI, lectura);
                    case 2 -> ejecutarConversion("ARS", "USD", clienteAPI, lectura);
                    case 3 -> ejecutarConversion("USD", "MXN", clienteAPI, lectura);
                    case 4 -> ejecutarConversion("MXN", "USD", clienteAPI, lectura);
                    case 5 -> ejecutarConversion("BRL", "ARS", clienteAPI, lectura);
                    case 6 -> ejecutarConversion("ARS", "BRL", clienteAPI, lectura);
                    case 7 -> System.out.println("Cerrando programa...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número entero.");
            }
        }

        // Al finalizar el bucle, guardamos y mostramos el historial
        HistorialDeConversiones historial = new HistorialDeConversiones();
        historial.guardarHistorial(registro);
        System.out.println("\n--- Resumen de su sesión ---");
        historial.mostrarHistorial(registro);
        System.out.println("¡Gracias por su visita!");
    }

    // MÉTODO AUXILIAR: Aquí centralizamos la lógica que antes repetías 6 veces
    private static void ejecutarConversion(String base, String target, ExchangeRateAPIClient cliente, Scanner lectura) {
        try {
            System.out.printf("Ingrese la cantidad en [%s] que desea convertir a [%s]:%n", base, target);
            double cantidad = Double.parseDouble(lectura.nextLine());

            TasaDeCambio resultado = cliente.resultadoDeConversion(base, target, cantidad);
            
            System.out.printf(">>> Resultado: %.2f %s son %.2f %s%n", 
                              cantidad, base, resultado.conversion_result(), target);
            
            registro.add(resultado);
        } catch (Exception e) {
            System.out.println("Error en la conversión: " + e.getMessage());
        }
    }
}