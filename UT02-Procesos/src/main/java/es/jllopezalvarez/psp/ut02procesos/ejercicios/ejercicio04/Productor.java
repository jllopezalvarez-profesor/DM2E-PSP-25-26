package es.jllopezalvarez.psp.ut02procesos.ejercicios.ejercicio04;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;

public class Productor {



    public static void main(String[] args) {
        for(int lineNumber = 1; lineNumber <= Constants.LINES_COUNT; lineNumber++) {
            try(FileWriter fw = new FileWriter(Constants.SHARED_FILE.toFile(), true)){
                fw.write(LocalTime.now().toString());
                fw.flush();
                Thread.sleep(Constants.SLEEP_TIME_MILISECONDS);
                fw.write(String.format(" - Iteración nº %s\n", lineNumber));
                if (lineNumber == Constants.LINES_COUNT) {
                    fw.write("FIN\n");
                }
                fw.flush(); // No es necesario, porque se cierra el fichero, y al cerrar se hace flush
                Thread.sleep(Constants.SLEEP_TIME_MILISECONDS);
            } catch (IOException e) {
                throw new RuntimeException("Error de E/S al manejar el fichero", e);
            } catch (InterruptedException e) {
                throw new RuntimeException("El proceso se ha interrumpido en un sleep", e);
            }
        }
    }
}
