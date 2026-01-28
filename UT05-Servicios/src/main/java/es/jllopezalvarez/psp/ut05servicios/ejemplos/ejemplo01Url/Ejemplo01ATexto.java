package es.jllopezalvarez.psp.ut05servicios.ejemplos.ejemplo01Url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class Ejemplo01ATexto {

    public static final String PUBLIC_IP_SERVICE_URL = "https://api.ipify.org";

    public static void main(String[] args) {
        try {
            // Los constructores de URL están deprecados. Hay que usar URI.
            URI uri = new URI(PUBLIC_IP_SERVICE_URL);
            // Obtener URL a partir de URI
            URL url = uri.toURL();
            // Abrir stream para lectura en modo texto
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                // Leer la única línea que devuelve este sitio.
                String ipPublica = br.readLine();
                System.out.println("IP pública: " + ipPublica);
            }

        } catch (Exception e) {
            System.err.println("Error al obtener la IP pública");
            e.printStackTrace();
        }
    }
}
