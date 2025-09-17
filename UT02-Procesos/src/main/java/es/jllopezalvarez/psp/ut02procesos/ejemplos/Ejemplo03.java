package es.jllopezalvarez.psp.ut02procesos.ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Ejemplo de lanzamiento de otro proceso con ProcessBuilder, y captura de su salida
 */
public class Ejemplo03 {
    // private static final String COMMAND_LINE_WINDOWS = "notepad.exe";
    private static final String COMMAND_LINE_WINDOWS = "dir";
    // private static final String COMMAND_LINE_LINUX_KDE = "kate";
    private static final String COMMAND_LINE_LINUX_KDE = "ls";

    public static void main(String[] args) {
        // Elegir qué comando ejecutar en función de si es un SO Windows o Linux
        String commandLine = isWindows() ? COMMAND_LINE_WINDOWS : COMMAND_LINE_LINUX_KDE;

        // Traza
        System.out.printf("Se va a lanzar el proceso %s.\n", commandLine);

        // Lanzar el proceso con ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder(commandLine);
        // Opcional, cambiar el directorio
        // processBuilder.directory(new File(isWindows() ? "C:\\\\" : "/"));
        try {
            Process process = processBuilder.start();

            // Traza
            System.out.println("Proceso lanzado. Ahora se captura su salida, para poder mostrarla en este proceso.");

            // Obtener el stream
            // Se pide el InputStream porque desde el punto de vista de ESTE proceso, del padre, el stream es de
            // entrada/lectura y desde el punto de vista del otro proceso, del hijo, es de salida/escritura.
            // Se usa try-with-resources para evitar que se dejen streams abiertos.
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                // Leer lo que el proceso hijo escribe. Esto bloquea el proceso padre (este programa) hasta que el
                // otro proceso escriba una línea, o hasta que termine. Cuando termina, la lectura con readLine
                // devuelve null, porque se habrá cerrado el stream.
                String line = reader.readLine();
                // Mientras que no se cierre el stream, se sigue leyendo y mostrando el resultado
                while (line != null) {
                    System.out.print("Línea generada por el proceso y leída como entrada: ");
                    System.out.println(line);
                    // Volver a leer. Se vuelve a bloquear este proceso hasta que el hijo escribe / cierra el stream.
                    line = reader.readLine();
                }
            }

            // Esperar a que el proceso termine.
            // Aunque el proceso haya cerrado el stream de salida estándar, no hay garantías de que en realidad
            // haya terminado, por eso se espera a que termine (porque en este caso se quiere hacer así).
            int result = process.waitFor();

            // Traza
            System.out.printf("El proceso ha terminado con el código '%s'.\n", result);

        } catch (IOException e) {
            // Se produce una excepción de E/S.
            // Por simplificar, simplemente se relanza la excepción "envolviéndola" en una RuntimeException.
            throw new RuntimeException("Error al lanzar el proceso", e);
        } catch (InterruptedException e) {
            // Se produce una excepción por interrupción del proceso padre.
            // Por simplificar, simplemente se relanza la excepción "envolviéndola" en una RuntimeException.
            throw new RuntimeException("El proceso padres se ha interrumpido", e);
        }


    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
}
