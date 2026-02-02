package es.jllopezalvarez.psp.ut05servicios.ejemplos.ejemplo02HttpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class Ejemplo02B1BinarioSincrono {

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

            // Enviar petición al servidor. El hilo se bloquea hasta que termina la petición.
            // Se indica que el cuerpo de la respuesta debe guardarse en disco.
            HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Path.of(FICHERO_DESTINO)));

            System.out.printf("La respuesta del servidor es un %d\n", response.statusCode());

            System.out.println("Cabeceras recibidas:");
            System.out.println(response.headers());

            System.out.println("Logo descargado correctamente");

        } catch (Exception e) {
            System.err.println("Error al descargar el archivo");
            e.printStackTrace();
        }
    }
}
