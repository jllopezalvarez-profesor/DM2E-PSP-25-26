package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio12;

import java.util.concurrent.Semaphore;

public class Controlador extends Thread {

    private final Semaphore semaforoCoches;
    private final Semaphore semaforoPeatones;

    private boolean pasandoCoches;
    private final long periodoCambioSemaforo;

    public Controlador(Semaphore semaforoCoches, Semaphore semaforoPeatones, long periodoCambioSemaforo) {
        this.semaforoCoches = semaforoCoches;
        this.semaforoPeatones = semaforoPeatones;

        this.pasandoCoches = semaforoCoches.availablePermits() > 0;
        this.periodoCambioSemaforo = periodoCambioSemaforo;
    }

    @Override
    public void run() {
        try {

            while (!this.isInterrupted()) {
                // Espero un tiempo para cambiar
                Thread.sleep(periodoCambioSemaforo);

                System.out.println("Controlador:intento cambiar el semáforo.");
                if (this.pasandoCoches) {
                    semaforoCoches.acquire();
                    System.out.println("Controlador: pongo el semáforo en verde para peatones y en rojo para coches.");
                    semaforoPeatones.release();
                    pasandoCoches = false;
                } else {
                    semaforoPeatones.acquire();
                    System.out.println("Controlador: pongo el semáforo en verde para coches y en rojo para peatones.");
                    semaforoCoches.release();
                    pasandoCoches = true;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("El controlador ha sido interrumpido.");
        }
    }
}
