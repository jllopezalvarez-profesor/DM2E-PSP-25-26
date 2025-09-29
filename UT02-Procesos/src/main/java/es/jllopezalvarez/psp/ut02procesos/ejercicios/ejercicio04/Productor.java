package es.jllopezalvarez.psp.ut02procesos.ejercicios.ejercicio04;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;

public class Productor {

    private static final Path SHARED_FILE = Path.of("programs-output", "mensajes.txt");
    private static final int LINES_COUNT = 20;
    private static final long SLEEP_TIME_MILISECONDS = 1000;

    public static void main(String[] args) {
        for(int lineNumber = 1; lineNumber <= LINES_COUNT; lineNumber++) {
            try(FileWriter fw = new FileWriter(SHARED_FILE.toFile(), true)){
                fw.write(LocalTime.now().toString());
                fw.flush();
                Thread.sleep(SLEEP_TIME_MILISECONDS);
                fw.write(String.format(" - Iteración nº %s\n", lineNumber));
                if (lineNumber == LINES_COUNT) {
                    fw.write("FIN\n");
                }
                // fw.flush(); // No es necesario, porque se cierra el fichero, y al cerrar se hace flush
            } catch (IOException e) {
                throw new RuntimeException("Error de E/S al manejar el fichero", e);
            } catch (InterruptedException e) {
                throw new RuntimeException("El proceso se ha interrumpido en un sleep", e);
            }
        }
    }
}
