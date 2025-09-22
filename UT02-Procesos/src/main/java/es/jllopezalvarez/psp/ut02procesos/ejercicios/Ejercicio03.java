package es.jllopezalvarez.psp.ut02procesos.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Ejercicio03 {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows() ? "-n" : "-c", "6", "google.com");
        try {
            Process pingProcess = processBuilder.start();

            try (InputStream inputStream = pingProcess.getInputStream();
                 InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    System.out.printf("Linea generada por el ping: %s\n", line);
                    line = bufferedReader.readLine();
                }
            }

            // Aunque se ha cerrado la salida del proceso hijo (que es la entrada de este proceso)
            // Hay que esperar a que el proceso termine.
            int result = pingProcess.waitFor();

            // Con espera activa (Thread.sleep)
            // En general, mejor no usar espera activa, porque waitFor es más eficiente
            // La espera activa implica que en ocasiones los procesos se despierten
            // para luego dormirse, sin hacer nada.
            // En escenarios en los que hay que hacer cosas cada cierto tiempo mientras
            // se espera, sí tiene sentido, pero en este caso es mejor usar waitfor(tiempo).
            while (pingProcess.isAlive()) {
                Thread.sleep(100);
            }

            System.out.printf("Terminado con código %d\n", result);


        } catch (IOException e) {
            throw new RuntimeException("Error de E/S al lanzar el proceso", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error, interrumpido", e);
        }

    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
}
