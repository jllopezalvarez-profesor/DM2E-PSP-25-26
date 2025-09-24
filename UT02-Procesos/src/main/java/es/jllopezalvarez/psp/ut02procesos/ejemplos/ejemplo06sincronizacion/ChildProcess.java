package es.jllopezalvarez.psp.ut02procesos.ejemplos.ejemplo06sincronizacion;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Random;

public class ChildProcess {
    private static final Random RANDOM = new Random();

    // Path del fichero de datos que se va a procesar.
    private static final Path PATH_FICHERO_DATOS = Paths.get("./programs-output", "ejemplo-06.txt");

    public static void main(String[] args) {
        // Obtener el número de filas que se deben escribir
        int writeCount = RANDOM.nextInt(5) + 5; // Al menos 5, como mucho 10

        // Calcular tiempo que el proceso se duerme entre iteraciones
        long sleepTime = RANDOM.nextLong(100, 1000); // Entre 0.1 y 1 segundo

        // Obtener el identificador del proceso actual, para usarlo en las trazas
        long currentProcessId = ProcessHandle.current().pid();

        // Abrir un PrintWriter para el fichero.
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(PATH_FICHERO_DATOS.toFile(), true))) {

            // Escribir un número de líneas en el fichero.
            for (int count = 0; count < writeCount; count++) {
                // Traza para probar el programa cuando se lanza "solo".
                System.out.printf("Escribo en fichero y duermo %s ms.\n", sleepTime);

                // Escribir datos en el fichero.
                pw.printf("PID: %d - %s\n", currentProcessId, LocalTime.now());

                // Ojo: si se comenta / elimina el flush(), como PrintWriter es E/S con buffer, se guarda todo a la vez,
                // Y parece que los procesos se sincronizan, pero lo hacen porque, sin buffer, en realidad solo hay
                // una operación de escritura, no varias.
                pw.flush();

                // Esperar un poco para dar tiempo a interferencia de otros procesos.
                Thread.sleep(sleepTime);
            }
        } catch (FileNotFoundException e) {
            System.err.printf("El fichero no se ha podido crear / abrir: %s\n", e.getMessage());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.err.printf("El proceso ha sido interrumpido: %s\n", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
