package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio05.v04;

import java.util.ArrayList;
import java.util.List;

public class MainProgram {

    private static final int NUM_HILOS = 10000;
    private static final int NUM_VUELTAS = 100;

    private static long cuenta = 0;

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        // arrancar todos los hilos
        for (int i = 0; i < NUM_HILOS; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < NUM_VUELTAS; j++) {
                    cuenta++;
                }
            });

            thread.start();
            threads.add(thread);
        }

        // Esperar a los hilos
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrumpido mientras esperaba", e);
        }

        // Mostrar resultado
        System.out.printf("El valor de la cuenta al terminar es: %d\n", cuenta);


    }


}
