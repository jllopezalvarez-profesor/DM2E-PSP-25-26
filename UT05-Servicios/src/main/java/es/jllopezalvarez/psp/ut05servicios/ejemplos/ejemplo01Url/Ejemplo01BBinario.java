package es.jllopezalvarez.psp.ut05servicios.ejemplos.ejemplo01Url;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class Ejemplo01BBinario {

    public static final String LOGO_URL =
            "https://www.educa2.madrid.org/educamadrid/images/logos/educamadrid_header.png";

    public static final String FICHERO_DESTINO = "logo.png";

    public static void main(String[] args) {
        try {
            // Los constructores de URL están deprecados. Hay que usar URI.
            URI uri = new URI(LOGO_URL);
            // Obtener URL a partir de URI
            URL url = uri.toURL();

            // Stream binario de entrada (desde Internet)
            // Stream binario de salida (hacia el disco)
            try (InputStream in = url.openStream();
                 FileOutputStream out = new FileOutputStream(FICHERO_DESTINO)) {

                // Copia por bloques de bytes (bloques de 4KBytes)
                byte[] buffer = new byte[4096];
                int bytesLeidos;
                // Leer bloque de internet y escribirlo en disco.
                // Cuando read devuelve cero es que se ha terminado el stream.
                while ((bytesLeidos = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesLeidos);
                }

                System.out.println("Archivo descargado correctamente: " + FICHERO_DESTINO);
            }

        } catch (Exception e) {
            System.err.println("Error al descargar el archivo");
            e.printStackTrace();
        }
    }
}
