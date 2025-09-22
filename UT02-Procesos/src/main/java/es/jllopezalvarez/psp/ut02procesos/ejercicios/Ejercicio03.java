package es.jllopezalvarez.psp.ut02procesos.ejercicios;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Ejercicio03 {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("ping", "-c", "40", "google.com");
        try {
            Process pingProcess = processBuilder.start();

            // Abrir un BuferedReader que "envuelva" el StreamReader de la salida estándar del proceso

            // Leer línea por línea del BufferedReader hasta que se termina (devuelve null)

            // Esperar a que acabe el proceso hijo.


            int result =  0; // Esto sale de algún sitio

            System.out.printf("Terminado con código %d\n", result);



        } catch (IOException e) {
            throw new RuntimeException("Error de E/S al lanzar el proceso", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error, interrumpido", e);
        }

    }
}
