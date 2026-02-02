package es.jllopezalvarez.psp.ut05servicios.ejercicios.ejercicio01;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Program {

    private static final String SERVICE_URL = "https://dog.ceo/api/breeds/image/random";

    public static void main(String[] args) {

        try {
            // 1. Leer el JSON desde la API
            URI serviceUri = URI.create(SERVICE_URL);
            URL serviceUrl = serviceUri.toURL();
            String apiResponseText;

            try (BufferedReader serviceReader = new BufferedReader(new InputStreamReader(serviceUrl.openStream()))) {
                apiResponseText = serviceReader.lines().reduce((p, n) -> p + n).orElseThrow();
            }

            // 2. Convertir el JSON a objeto con Jackson
            ObjectMapper mapper = new ObjectMapper();
            DogResponse response = mapper.readValue(apiResponseText, DogResponse.class);

            // 3. Obtener la URL de la imagen
            String imageUrl = response.getMessage();
            System.out.println("URL de la imagen: " + imageUrl);

            // 4. Descargar la imagen
            URL imageUrlObj = URI.create(imageUrl).toURL();
            try (InputStream in = imageUrlObj.openStream()) {
                Path destino = Path.of(imageUrlObj.getPath()).getFileName();
                Files.copy(in, destino);
            }

            System.out.println("Imagen descargada correctamente.");

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
