package es.jllopezalvarez.psp.ut02procesos.ejercicios.ejercicio04;

import java.nio.file.Path;

public interface Constants {
    Path LOCK_FILE = Path.of("programs-output", "lock.dat");
    Path SHARED_FILE = Path.of("programs-output", "mensajes.txt");
    int LINES_COUNT = 40;
    long SLEEP_TIME_MILISECONDS = 250;
}
