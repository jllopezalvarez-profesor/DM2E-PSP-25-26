package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio12;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    private static final int NUM_COCHES = 50;
    private static final int NUM_PEATONES = 50;
    private static final long PERIODO_CAMBIO_SEMAFORO = 500;


    public static void main(String[] args) throws InterruptedException {
        // Inicialmente el semáforo de coches abierto
        Semaphore semaforoCoches = new Semaphore(1, true);
        // Inicialmente el semáforo de peatones cerrado
        Semaphore semaforoPeatones = new Semaphore(0, true);

        // Arrancar el controlador
        Controlador controlador = new Controlador(semaforoCoches, semaforoPeatones, PERIODO_CAMBIO_SEMAFORO);
        controlador.start();

        List<Coche> coches = new ArrayList<>();
        for (int i = 0; i < NUM_COCHES; i++) {
            coches.add(new Coche("COCHE-" + i, semaforoCoches));
        }
        List<Peaton> peatones = new ArrayList<>();
        for (int i = 0; i < NUM_PEATONES; i++) {
            peatones.add(new Peaton("PEATON-" + i, semaforoPeatones));
        }

        for(Coche coche : coches) {
            coche.start();
        }
        for(Peaton peaton : peatones) {
            peaton.start();
        }

        // Esperar a que terminen
        for(Coche coche : coches) {
            coche.join();
        }
        for(Peaton peaton : peatones) {
            peaton.join();
        }

        // Interrumpir el controlador
        controlador.interrupt();

        controlador.join();




    }

}
