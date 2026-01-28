package es.jllopezalvarez.psp.ut05servicios.ejemplos.ejemplo02HttpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class Ejemplo02B2BinarioAsincrono {

    public static final String LOGO_URL =
            "https://www.educa2.madrid.org/educamadrid/images/logos/educamadrid_header.png";

    public static final String FICHERO_DESTINO = "logo.png";


    public static void main(String[] args) {
        // Crear cliente HTTP
        try (HttpClient client = HttpClient.newHttpClient()) {
            // Crear petición que se enviará al servidor.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(LOGO_URL))
                    .GET()
                    .build();

            // Enviar petición al servidor. sendAsync envía la petición en segundo plano,
            // sin bloquear el hilo, que continúa mientras se procesa la petición y la respuesta.
            // Se indica que se procese el cuerpo de la petición como un fichero que se descarga.
            // - thenAccept(expresión lambda)
            //      - Cuando se ha extraído el cuerpo y se ha volcado a disco.
            // - exceptionally(expresión lambda)
            //      - Controla excepciones en la petición asíncrona o el procesamiento de los datos.
            //        El tipo de valor depende del CompletableFuture anterior (el de thenAccept en este caso)
            client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(Path.of(FICHERO_DESTINO))
            ).thenAccept(response ->
                    System.out.printf("Descarga completada con código HTTP: %d", response.statusCode())
            ).exceptionally(e -> {
                System.err.println("Error al descargar fichero (exceptionally).");
                e.printStackTrace();
                return null;
            });

            // Mensaje en hilo principal.
            System.out.println("Esto puede aparecer antes que el mensaje de descarga completada, dependiendo de la velocidad de red.");

            // Evitar que el programa termine antes de que se haya completado la petición
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error al descargar fichero (catch externo).");
            e.printStackTrace();
        }
    }
}
