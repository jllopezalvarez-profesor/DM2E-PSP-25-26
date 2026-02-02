package es.jllopezalvarez.psp.ut05servicios.ejercicios.ejercicio02;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class Program {

    private static final String SERVICE_URL = "https://dog.ceo/api/breeds/image/random";

    public static void main(String[] args) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SERVICE_URL))
                .GET()
                .build();

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> jsonResponse =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // 2. Convertir el JSON a objeto con Jackson
            ObjectMapper mapper = new ObjectMapper();
            DogResponse response = mapper.readValue(jsonResponse.body(), DogResponse.class);

            // 3. Obtener la URL de la imagen
            String imageUrl = response.getMessage();
            System.out.println("URL de la imagen: " + imageUrl);

            request = HttpRequest.newBuilder()
                    .uri(URI.create(imageUrl))
                    .GET()
                    .build();

            Path destino = Path.of(imageUrl).getFileName();
            HttpResponse<Path> binaryResponse = client.send(request, HttpResponse.BodyHandlers.ofFile(destino));

            if (binaryResponse.statusCode() != 200){
                System.out.printf("El servidor ha respondido con un código distinto a 200: %d\n", binaryResponse.statusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


//        try {
//            // 1. Leer el JSON desde la API
//            URI serviceUri = URI.create(SERVICE_URL);
//            URL serviceUrl = serviceUri.toURL();
//            String apiResponseText;
//
//            try (BufferedReader serviceReader = new BufferedReader(new InputStreamReader(serviceUrl.openStream()))) {
//                apiResponseText = serviceReader.lines().reduce((p, n) -> p + n).orElseThrow();
//            }
//
//
//            // 4. Descargar la imagen
//            URL imageUrlObj = URI.create(imageUrl).toURL();
//            try (InputStream in = imageUrlObj.openStream()) {
//                Path destino = Path.of(imageUrlObj.getPath()).getFileName();
//                Files.copy(in, destino);
//            }
//
//            System.out.println("Imagen descargada correctamente.");
//
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }


}
