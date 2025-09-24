package es.jllopezalvarez.psp.ut02procesos.ejemplos.ejemplo10sincronizacion;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Random;

public class ChildProcess {
    private static final Random RANDOM = new Random();

    // Path del fichero de datos que se va a procesar.
    private static final Path PATH_FICHERO_DATOS = Paths.get("./programs-output", "ejemplo-10.txt");

    public static void main(String[] args) throws IOException, InterruptedException {
        // Obtener el número de filas que se deben escribir
        int writeCount = RANDOM.nextInt(5) + 5; // Al menos 5, como mucho 10

        // Calcular tiempo que el proceso se duerme entre iteraciones
        long sleepTime = RANDOM.nextLong(100, 1000); // Entre 0.1 y 1 segundo

        // Obtener el identificador del proceso actual, para usarlo en las trazas
        long currentProcessId = ProcessHandle.current().pid();

        // Usar el recurso compartido.
        // Abrir un PrintWriter para el fichero. También un Channel y un FileLock sobre el fichero a utilizar
        try (FileOutputStream fos = new FileOutputStream(PATH_FICHERO_DATOS.toFile(), true);
             PrintWriter pw = new PrintWriter(fos);
             FileChannel channel = fos.getChannel();
             FileLock lock = channel.tryLock()) {

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
        // Al terminar, no hay que hacer nada. Al salir del try, se cierran automáticamente lock, channel y streams.

    }
}
