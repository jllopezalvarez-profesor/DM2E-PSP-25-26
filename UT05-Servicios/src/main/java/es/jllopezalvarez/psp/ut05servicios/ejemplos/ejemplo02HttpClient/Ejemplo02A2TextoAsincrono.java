package es.jllopezalvarez.psp.ut05servicios.ejemplos.ejemplo02HttpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ejemplo02A2TextoAsincrono {

    private static final String PUBLIC_IP_SERVICE_URL = "https://api.ipify.org";

    public static void main(String[] args) {
        // Crear cliente HTTP
        try (HttpClient client = HttpClient.newHttpClient()) {
            // Crear petición que se enviará al servidor.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(PUBLIC_IP_SERVICE_URL))
                    .GET()
                    .build();

            // Enviar petición al servidor. sendAsync envía la petición en segundo plano,
            // sin bloquear el hilo, que continúa mientras se procesa la petición y la respuesta.
            // sendAsync devuelve un objeto CompletableFuture<HttpResponse<String>>.
            // Los métodos definen que hacer en cada caso:
            // - thenApply(HttpResponse::body)
            //      - Cuando acaba la petición obtiene el cuerpo de la petición.
            //      - Devuelve CompletableFuture<String>
            // - thenAccept(expresión lambda)
            //      - Cuando se ha extraído el cuerpo de la petición lo procesa.
            //      - Devuelve CompletableFuture<String>
            // - exceptionally(expresión lambda)
            //      - Controla excepciones en la petición asíncrona o el procesamiento de los datos.
            //      - Devuelve un valor alternativo.
            //        El tipo de valor depende del CompletableFuture anterior (el de thenAccept en este caso)
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(ip -> System.out.println("IP pública: " + ip))
                    .exceptionally(e -> {
                        System.err.println("Error al obtener la IP pública (exceptionally).");
                        e.printStackTrace();
                        return null; // Como
                    });

            // Mensaje en hilo principal.
            System.out.println("Esto puede aparecer antes que el mensaje de la IP, dependiendo de la velocidad de red.");

            // Evitar que el programa termine antes de que se haya completado la petición
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error al obtener la IP pública (catch externo).");
            e.printStackTrace();
        }
    }
}
