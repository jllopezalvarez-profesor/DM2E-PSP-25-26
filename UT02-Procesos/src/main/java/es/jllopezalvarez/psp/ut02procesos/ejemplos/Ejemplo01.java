package es.jllopezalvarez.psp.ut02procesos.ejemplos;

import java.io.IOException;

/**
 * Ejemplo de lanzamiento de otro proceso con Runtime.exec
 * En general, la mayoría de sobrecargas de exec están marcadas como "deprecated"
 * La recomendación actual es usar ProcessBuilder.
 */
public class Ejemplo01 {
    // private static final String COMMAND_LINE_WINDOWS = "notepad.exe";
    private static final String COMMAND_LINE_WINDOWS = "cmd";
    // private static final String COMMAND_LINE_LINUX_KDE = "kate";
    private static final String COMMAND_LINE_LINUX_KDE = "konsole";

    public static void main(String[] args) {
        // Elegir qué comando ejecutar en función de si es un SO Windows o Linux
        String commandLine = isWindows() ? COMMAND_LINE_WINDOWS : COMMAND_LINE_LINUX_KDE;

        // Traza
        System.out.printf("Se va a lanzar el proceso %s.\n",  commandLine);

        // Lanzar el proceso con Runtime.exec
        try {
            // El uso de exec está "deprecado". Pero lo vemos porque aún podemos encontrarlo en código en producción.
            Process process = Runtime.getRuntime().exec(commandLine);

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
