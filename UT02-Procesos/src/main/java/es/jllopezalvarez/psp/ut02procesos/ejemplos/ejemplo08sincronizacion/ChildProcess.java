package es.jllopezalvarez.psp.ut02procesos.ejemplos.ejemplo08sincronizacion;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Random;

public class ChildProcess {
    private static final Random RANDOM = new Random();

    // Path del fichero de datos que se va a procesar.
    private static final Path PATH_FICHERO_DATOS = Paths.get("./programs-output", "ejemplo-08.txt");

    public static void main(String[] args) throws IOException, InterruptedException {
        // Obtener el número de filas que se deben escribir
        int writeCount = RANDOM.nextInt(5) + 5; // Al menos 5, como mucho 10

        // Calcular tiempo que el proceso se duerme entre iteraciones
        long sleepTime = RANDOM.nextLong(100, 1000); // Entre 0.1 y 1 segundo

        // Obtener el identificador del proceso actual, para usarlo en las trazas
        long currentProcessId = ProcessHandle.current().pid();

        // Crear el objeto que se usa para bloquear
        File lock = new File("lock.tmp");
        // Mientras exista el fichero, esperar, y si no, crearlo. Se comprueba y se crea en la misma operación.
        while (!lock.createNewFile()) {
            // Dormir un tiempo, y volver a intentarlo
            // Se ha declarado que esta excepción se lanza en el main, para evitar añadir más bloques try/catch
            Thread.sleep(100);
        }

        // Usar el recurso compartido
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

        // Al terminar, borrar el fichero de bloqueo. Esto, idealmente, debería estar en un finally
        // No se ha hecho por simplificar.
        lock.delete();
    }
}
