package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio03;

public class Restador extends Thread {
    private static final long MAX = 2000000000;

    private final String threadName;

    public Restador(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        long longSub = 0;
        double doubleSub = 0;
        for (int i = 0; i <= MAX; i++) {
            longSub -= i;
            doubleSub -= i;
        }
        System.out.printf("La resta de los %d primeros números (con long) es %d.\n", MAX, longSub);
        System.out.printf("La resta de los %d primeros números (con double) es %f.\n", MAX, doubleSub);

        System.out.printf("Soy '%s' y ya termino\n", threadName);

    }

}
