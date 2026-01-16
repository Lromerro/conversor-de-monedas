import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HistorialDeConversiones { // Corregido el nombre "Converciones"
    
    // Reutilizamos Gson para no crearlo en cada llamada
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void guardarHistorial(List<TasaDeCambio> registro) {
        if (registro == null || registro.isEmpty()) return;

        // Usamos un nombre de archivo más estándar y Try-with-resources
        try (FileWriter escritor = new FileWriter("historial.json")) {
            GSON.toJson(registro, escritor); // Más eficiente que gson.toJson(registro)
            System.out.println("Historial actualizado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public void mostrarHistorial(List<TasaDeCambio> registro) {
        if (registro == null || registro.isEmpty()) {
            System.out.println("No hay conversiones en el historial.");
            return;
        }

        System.out.println("\n--- HISTORIAL DE CONVERSIONES ---");
        registro.forEach(tasa -> System.out.printf(
            "Origen: %s -> Destino: %s | Tasa: %.4f | Resultado: %.2f%n",
            tasa.base_code(), tasa.target_code(), 
            tasa.conversion_rate(), tasa.conversion_result()
        ));
    }
}