import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ExchangeRateAPIClient {
    // 1. Reutilizamos las instancias: HttpClient y Gson son Thread-safe
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static final String CLAVE_API;

    static {
        Properties propiedades = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            propiedades.load(fis);
            String key = propiedades.getProperty("api.key");
            if (key == null || key.isBlank()) {
                throw new RuntimeException("La API Key está vacía en config.properties");
            }
            CLAVE_API = key;
        } catch (IOException e) {
            // Es mejor fallar rápido si falta la configuración crítica
            throw new ExceptionInInitializerError("No se pudo cargar el archivo de configuración: " + e.getMessage());
        }
    }

    public TasaDeCambio resultadoDeConversion(String baseCode, String targetCode, double amount) {
        // 2. Usamos String.format para una URL más legible y segura
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%.2f", 
                                    CLAVE_API, baseCode, targetCode, amount);

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            // 3. Enviamos la solicitud usando el cliente reutilizado
            HttpResponse<String> respuesta = CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());

            if (respuesta.statusCode() != 200) {
                throw new RuntimeException("Error en la API. HTTP Status: " + respuesta.statusCode());
            }

            return GSON.fromJson(respuesta.body(), TasaDeCambio.class);

        } catch (IOException | InterruptedException e) {
            // Manejamos las excepciones internamente para simplificar la firma del método
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException("Error al conectar con el servidor de tasas de cambio", e);
        }
    }
}