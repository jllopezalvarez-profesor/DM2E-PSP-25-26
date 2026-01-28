package es.jllopezalvarez.psp.ut05servicios.ejemplos.ejemplo02HttpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ejemplo02A1TextoSincrono {

    private static final String PUBLIC_IP_SERVICE_URL = "https://api.ipify.org";

    public static void main(String[] args) {
        // Crear cliente HTTP
        try (HttpClient client = HttpClient.newHttpClient()) {
            // Crear petición que se enviará al servidor.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(PUBLIC_IP_SERVICE_URL))
                    .GET()
                    .build();

            // Enviar petición al servidor. El hilo se bloquea hasta que termina la petición.
            // Se indica que el cuerpo de la respuesta será texto.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Obtener el valor devuelto por el servidor.
            System.out.println("IP pública: " + response.body());
        } catch (Exception e) {
            System.err.println("Error al obtener la IP pública.");
            e.printStackTrace();
        }
    }
}
