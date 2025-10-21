package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio05.old;

public class AddThread extends Thread {
    private static final int LOOP_COUNT = 10;

    private static long sum = 0;

    @Override
    public void run() {
        for (int i = 0; i < LOOP_COUNT; i++) {
            sum += i;
        }
    }

    public static long getSum() {
        return sum;
    }
}
