package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio12;

import java.util.concurrent.Semaphore;

public class Peaton extends Thread {

    private static final long TIEMPO_CRUCE = 100;
    private final String nombre;
    private final Semaphore semaforo;

    public Peaton(String nombre, Semaphore semaforo) {
        this.nombre = nombre;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        System.out.printf("El peatón llamado %s llega al cruce.\n", nombre);
        try {
            System.out.printf("El peatón con nombre %s intenta pasar.\n", nombre);
            while (!semaforo.tryAcquire()) {
                Thread.sleep(TIEMPO_CRUCE);
            }
//            semaforo.acquire();
            System.out.printf("El peatón con nombre %s está pasando.\n", nombre);
            Thread.sleep(TIEMPO_CRUCE);
            System.out.printf("El peatón con nombre %s ha pasado.\n", nombre);
            semaforo.release();
        } catch (InterruptedException e) {
            System.out.printf("El peatón con nombre %s ha sido interrumpido.\n", nombre);
        }
        System.out.printf("El peatón llamado %s sigue su camino.\n", nombre);
    }
}
