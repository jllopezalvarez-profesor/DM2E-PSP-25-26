package es.jllopezalvarez.psp.ut02procesos.ejercicios;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Ejercicio01 {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows() ? "-n" : "-c", "40", "google.com");

        try {
            Process pingProcess = processBuilder.start();


            int waitCount = 0;

//            while (pingProcess.isAlive()) {
//                boolean finished = pingProcess.waitFor(500, TimeUnit.MILLISECONDS);
//                waitCount++;
//                System.out.printf("%s - %s - He esperado %d veces\n",
//                        LocalTime.now(),
//                        finished?"El proceso ya ha terminado":"Aún no ha terminado",
//                        waitCount);
//            }

            boolean finished = false;

            while (!finished) {
                finished = pingProcess.waitFor(500, TimeUnit.MILLISECONDS);
                waitCount++;
                System.out.printf("%s - %s - He esperado %d veces\n",
                        LocalTime.now(),
                        finished ? "El proceso ya ha terminado" : "Aún no ha terminado",
                        waitCount);
            }

            int result = pingProcess.exitValue();

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
