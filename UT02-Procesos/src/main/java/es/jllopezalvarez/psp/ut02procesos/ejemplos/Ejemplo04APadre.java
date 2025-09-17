package es.jllopezalvarez.psp.ut02procesos.ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Otro ejemplo de lanzamiento de otro proceso con ProcessBuilder, y captura de su salida
 * Con dos particularidades:
 * - El proceso hijo es otro programa Java que dentro de este mismo proyecto.
 * - Se usa un método de processBuilder para redirigir la salida de errores a la estándar, combinándolas
 */
public class Ejemplo04APadre {
    // Ejecutable de Java, la JVM. Debería estar en Path, por lo que no debe ser necesaria una ruta completa
    private static final String JAVA_COMMAND_LINE = "java";

    // Valor del parámetro classpath, que indica a la JVM qué programa ejecutar. Se divide en dos partes.
    // Ubicación de la clase que se quiere lanzar
    private static final String CLASSPATH_LOCATION = "./target/classes";
    // Clase que se quiere lanzar
    private static final String CLASSPATH_CLASS = "es.jllopezalvarez.psp.ut02procesos.ejemplos.Ejemplo04BHijo";


    public static void main(String[] args) {
        // Traza
        System.out.printf("Se va a lanzar el proceso %s %s %s %s.\n", JAVA_COMMAND_LINE, "-classpath", CLASSPATH_LOCATION, CLASSPATH_CLASS);

        // Preparar el lanzamiento del proceso con ProcessBuilder.
        ProcessBuilder processBuilder = new ProcessBuilder(JAVA_COMMAND_LINE, "-classpath", CLASSPATH_LOCATION, CLASSPATH_CLASS);
        // Redirigir la salida de errores a la salida estándar. Para poder capturarlas a la vez
        processBuilder.redirectErrorStream(true);

        try {
            // Lanzar el proceso
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
                    System.out.print("Línea generada por el proceso hijo y leída como entrada: ");
                    System.out.println(line);
                    // Volver a leer. Se vuelve a bloquear este proceso hasta que el hijo escribe / cierra el stream.
                    line = reader.readLine();
                }
            }

            // Esperar a que el proceso termine.
            // Aunque el proceso haya cerrado el stream de salida estándar, no hay garantías de que en realidad
            // haya terminado, por eso se espera a que termine. Si no importa que el otro proceso (hijo) termine
            // después que este (padre), se puede quitar el waitFor.
            int result = process.waitFor();

            // Traza. Si no se espera con waitFor, al usar exitValue() puede lanzar excepción si no ha terminado el hijo.
            System.out.printf("El proceso ha terminado con el código '%s'.\n", result);

        } catch (IOException e) {
            // Se produce una excepción de E/S.
            // Por simplificar, simplemente se relanza la excepción "envolviéndola" en una RuntimeException.
            throw new RuntimeException("Error al lanzar el proceso", e);
        } catch (InterruptedException e) {
            // Se produce una excepción por interrupción del proceso padre.
            // Por simplificar, simplemente se relanza la excepción "envolviéndola" en una RuntimeException.
            throw new RuntimeException("El proceso padre se ha interrumpido", e);
        }
    }
}
