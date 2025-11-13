package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio12;

import java.util.concurrent.Semaphore;

public class Coche extends Thread {

    private static final long TIEMPO_CRUCE = 100;
    private static final long TIEMPO_TRAS_CRUCE = 300;
    private final String matricula;
    private final Semaphore semaforo;

    public Coche(String matricula, Semaphore semaforo) {
        this.matricula = matricula;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        System.out.printf("El coche con matrícula %s arranca.\n",  matricula);
        try {
            System.out.printf("El coche con matricula %s intenta pasar.\n",  matricula);
            while (!semaforo.tryAcquire()) {

                Thread.sleep(TIEMPO_CRUCE);
            }
//            semaforo.acquire();
            System.out.printf("El coche con matricula %s está pasando.\n",  matricula);
            Thread.sleep(TIEMPO_CRUCE);
            System.out.printf("El coche con matricula %s ha pasado.\n",  matricula);
            semaforo.release();
            Thread.sleep(TIEMPO_TRAS_CRUCE);
            System.out.printf("El coche con matricula %s sigue su camino.\n",  matricula);
        } catch (InterruptedException e) {
            System.out.printf("El coche con matricula %s ha sido interrumpido.\n", matricula);
        }
        System.out.printf("El coche con matrícula %s para.\n",  matricula);
    }
}
