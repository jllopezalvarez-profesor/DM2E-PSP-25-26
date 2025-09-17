package es.jllopezalvarez.psp.ut02procesos.ejemplos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Otro ejemplo de lanzamiento de otro proceso con ProcessBuilder, y redirección de su salida
 * El proceso hijo es igual que el del ejemplo 4 (captura de salida).
 */
public class Ejemplo05APadre {
    // Ejecutable de Java, la JVM. Debería estar en Path, por lo que no debe ser necesaria una ruta completa
    private static final String JAVA_COMMAND_LINE = "java";

    // Valor del parámetro classpath, que indica a la JVM qué programa ejecutar. Se divide en dos partes.
    // Ubicación de la clase que se quiere lanzar
    private static final String CLASSPATH_LOCATION = "./target/classes";
    // Clase que se quiere lanzar
    private static final String CLASSPATH_CLASS = "es.jllopezalvarez.psp.ut02procesos.ejemplos.Ejemplo05BHijo";

    // Rutas de los ficheros en el que volcar las salidas estándar y de errores.
    // Si no se especifica nada, los ficheros se crean en el directorio raíz del proyecto.
    // Aquí vamos a usar un directorio "programs-output"
    private static final String PROGRAMS_OUTPUT_PATH = "programs-output";
    private static final Path STDOUT_REDIRECT_PATH = Paths.get(PROGRAMS_OUTPUT_PATH, "stdout.txt");
    private static final Path STDERR_REDIRECT_PATH = Paths.get(PROGRAMS_OUTPUT_PATH, "stderr.txt");

    public static void main(String[] args) {
        // Traza
        System.out.printf("Se va a lanzar el proceso %s %s %s %s.\n", JAVA_COMMAND_LINE, "-classpath", CLASSPATH_LOCATION, CLASSPATH_CLASS);

        // Preparar el lanzamiento del proceso con ProcessBuilder.
        ProcessBuilder processBuilder = new ProcessBuilder(JAVA_COMMAND_LINE, "-classpath", CLASSPATH_LOCATION, CLASSPATH_CLASS);

        // Antes de redirigir salidas, asegurarse de que existe el directorio donde se tienen que escribir los ficheros:
        File outputDirectory = new File (PROGRAMS_OUTPUT_PATH);
        if (!outputDirectory.mkdirs()){
            System.out.printf("Error al crear el directorio de salida '%s'\n", outputDirectory.getAbsolutePath());
        };

        // Redirigir la salida estándar a un fichero
        System.out.printf("Redirigiendo salida estándar del proceso hijo a %s.\n", STDOUT_REDIRECT_PATH);
        processBuilder.redirectOutput(STDOUT_REDIRECT_PATH.toFile());

        // Redirigir la salida de errores a un fichero
        System.out.printf("Redirigiendo salida de errores del proceso hijo a %s.\n", STDERR_REDIRECT_PATH);
        processBuilder.redirectError(STDERR_REDIRECT_PATH.toFile());

        try {
            // Lanzar el proceso
            Process process = processBuilder.start();

            // Traza
            System.out.println("Proceso lanzado. Esperando a que termine.");

            // Esperar a que el proceso termine.
            int result = process.waitFor();

            // Traza. Si no se espera con waitFor, al usar exitValue() puede lanzar excepción si no ha terminado el hijo.
            System.out.printf("El proceso ha terminado con el código '%s'.\n", result);

        } catch (IOException e) {
            // Se produce una excepción de E/S.
            // Por simplificar, simplemente se relanza la excepción "envolviéndola" en una RuntimeException.
            throw new RuntimeException("Error al lanzar el proceso", e);
        } catch (InterruptedException e) {
            // Se produce una excepción por interrupción.
            // Por simplificar, simplemente se relanza la excepción "envolviéndola" en una RuntimeException.
            throw new RuntimeException("El proceso padre se ha interrumpido", e);
        }
    }
}
