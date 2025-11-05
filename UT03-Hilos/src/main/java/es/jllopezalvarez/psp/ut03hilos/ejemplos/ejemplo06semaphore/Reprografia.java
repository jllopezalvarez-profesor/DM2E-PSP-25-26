package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo06semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Reprografia {
    private static final long MIN_TIEMPO_IMPRESION = 1000;
    private static final long MAX_TIEMPO_IMPRESION = 2000;

    private final Semaphore semaforo; // Semáforo para controlar el acceso a las impresoras disponibles.

    private static Random rnd = new Random();

    public Reprografia(int numImpresoras) {
        this.semaforo = new Semaphore(numImpresoras, true); // Se inicializa con el número de impresoras.
    }

    public void imprimir(String nombreUsuario, int numDocumento) throws InterruptedException {
        System.out.printf("%s intenta acceder al centro de impresión...\n", nombreUsuario);
        try {
            semaforo.acquire(); // Pide acceso a una de las impresoras libres. Si no hay, se bloquea.
            System.out.printf("%s está imprimiendo el documento %d...\n", nombreUsuario, numDocumento);
            Thread.sleep(rnd.nextLong (MIN_TIEMPO_IMPRESION, MAX_TIEMPO_IMPRESION)); // Simula impresión.
            System.out.printf("%s ha terminado de imprimir.\n", nombreUsuario);
        } catch (InterruptedException e) {
            System.out.printf("%s ha sido interrumpido al imprimir. Se relanza la excepción.\n", nombreUsuario);
            throw e;
            // Thread.currentThread().interrupt(); // Otra forma de propagar la interrupción del hilo.
        } finally {
            semaforo.release(); // Libera permiso. Se hace en finally para garantizar que, si hay error, se libera.
        }
    }
}
