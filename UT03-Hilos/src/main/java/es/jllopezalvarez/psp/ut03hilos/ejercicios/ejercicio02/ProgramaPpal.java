package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio02;

public class ProgramaPpal {
    public static void main(String[] args) {
        Sumador sumador = new Sumador("Hilo sumador");
        Restador restador = new Restador("Hilo restador");

        long start = System.currentTimeMillis();

        sumador.start();
        restador.start();

        try {
            sumador.join();
            restador.join();
        } catch (InterruptedException e) {

            throw new RuntimeException("He sido interrumpido mientras esperaba.", e);
        }


        long stop = System.currentTimeMillis();

        long elapsed = stop - start;

        System.out.printf("Tiempo que han tardado: %d\n", elapsed);


    }

}
