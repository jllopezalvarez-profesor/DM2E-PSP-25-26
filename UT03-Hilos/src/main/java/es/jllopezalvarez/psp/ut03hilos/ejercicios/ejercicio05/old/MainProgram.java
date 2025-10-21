package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio05.old;

import java.util.ArrayList;
import java.util.List;

public class MainProgram {
    private static final int THREAD_NUM = 1000;

    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_NUM; i++) {
            threads.add(new AddThread());
        }
        for (Thread t : threads) {
            t.start();
        }
        try {
        for (Thread t : threads) {
                t.join();
        }
        } catch (InterruptedException e) {}



    }

}
