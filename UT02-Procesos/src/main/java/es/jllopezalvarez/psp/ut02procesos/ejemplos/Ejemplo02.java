package es.jllopezalvarez.psp.ut02procesos.ejemplos;

import java.io.IOException;

/**
 * Ejemplo de lanzamiento de otro proceso con ProcessBuilder
 */
public class Ejemplo02 {
    // private static final String COMMAND_LINE_WINDOWS = "notepad.exe";
    private static final String COMMAND_LINE_WINDOWS = "cmd";
    // private static final String COMMAND_LINE_LINUX_KDE = "kate";
    private static final String COMMAND_LINE_LINUX_KDE = "konsole";

    public static void main(String[] args) {
        // Elegir qué comando ejecutar en función de si es un SO Windows o Linux
        String commandLine = isWindows() ? COMMAND_LINE_WINDOWS : COMMAND_LINE_LINUX_KDE;

        // Traza
        System.out.printf("Se va a lanzar el proceso %s.\n", commandLine);

        // Preparar el lanzamiento del proceso
        ProcessBuilder processBuilder = new ProcessBuilder(commandLine);
        try {
            // Lanzar el proceso
            Process process = processBuilder.start();

            // Traza
            System.out.println("Proceso lanzado. Ahora se esperará a que termine.");

            // Esperar a que el proceso termine.
            int result = process.waitFor();

            // Traza
            System.out.printf("El proceso ha terminado con el código '%s'.\n", result);

        } catch (IOException e) {
            // Se produce una excepción de E/S.
            // Por simplificar, simplemente se relanza la excepción "envolviéndola" en una RuntimeException.
            throw new RuntimeException("Error al lanzar el proceso", e);
        } catch (InterruptedException e) {
            // Se interrumpe el proceso.
            // Por simplificar, simplemente se relanza la excepción "envolviéndola" en una RuntimeException.
            throw new RuntimeException("El proceso se ha visto interrumpido", e);
        }


    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
}
