package es.jllopezalvarez.psp.ut05servicios.ejercicios.ejercicio03;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class Program {

    private static final String SERVICE_URL = "https://dog.ceo/api/breeds/image/random";

    private static String dogImageUrl;

    public static void main(String[] args) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SERVICE_URL))
                .GET()
                .build();

        try (HttpClient client = HttpClient.newHttpClient()) {
            CompletableFuture<HttpResponse<String>> jsonFuture =
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofString());


            jsonFuture.thenApply(response -> {
                return response.body();
            }).thenAccept(json -> {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            DogResponse dogResponse = mapper.readValue(json, DogResponse.class);
                            dogImageUrl = dogResponse.getMessage();
                            System.out.println("La imagen del perro es: " + dogImageUrl);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                    }


            ).join();

            request = HttpRequest.newBuilder()
                    .uri(URI.create(dogImageUrl))
                    .GET()
                    .build();

            Path destino = Path.of(dogImageUrl).getFileName();
            CompletableFuture<HttpResponse<Path>> binaryFuture =
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(destino));

            binaryFuture.join();

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
