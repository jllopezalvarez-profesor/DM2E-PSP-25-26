package es.jllopezalvarez.psp.ut02procesos.ejercicios;

import java.io.IOException;

public class Ejercicio01 {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("ping", "-c", "200", "google.com");

        try {
            Process pingProcess = processBuilder.start();
//            Process pingProcess2 = processBuilder.start();
//            Process pingProcess3 = processBuilder.start();

            pingProcess.waitFor();
            // Cambiar esto para que se use waitFor(500, TimeUnit.MILLISECOND)


            System.out.printf("Terminado");
        } catch (IOException e) {
            throw new RuntimeException("Error de E/S al lanzar el proceso", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error, interrumpido", e);
        }

    }
}
