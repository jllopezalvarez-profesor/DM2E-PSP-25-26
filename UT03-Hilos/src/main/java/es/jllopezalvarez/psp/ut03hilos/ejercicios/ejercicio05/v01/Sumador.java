package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio05.v01;

public class Sumador extends Thread {
    private static long cuenta = 0;
    private static final int NUM_VUELTAS = 100;

    @Override
    public void run() {
        for (int i = 0; i < NUM_VUELTAS; i++) {
            cuenta++;
        }
    }

    public static long getCuenta() {
        return cuenta;
    }
}
