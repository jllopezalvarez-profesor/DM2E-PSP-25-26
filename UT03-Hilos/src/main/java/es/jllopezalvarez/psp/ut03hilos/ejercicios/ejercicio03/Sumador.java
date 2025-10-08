package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio03;

public class Sumador extends Thread {
    private static final long MAX = 2000000000;

    private final String threadName;

    public Sumador(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        long longSum = 0;
        double doubleSum = 0;
        for (int i = 0; i <= MAX; i++) {
            longSum+= i;
            doubleSum+= i;
        }
        System.out.printf("La suma de los %d primeros números (con long) es %d.\n", MAX,longSum);
        System.out.printf("La suma de los %d primeros números (con double) es %f.\n", MAX,doubleSum);

        System.out.printf("Soy '%s' y ya termino\n", threadName);
    }
}
