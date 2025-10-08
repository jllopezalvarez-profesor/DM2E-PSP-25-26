package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio03;

import java.time.Duration;

public class ProgramaPpal {
    public static void main(String[] args) {
        Sumador sumador = new Sumador("Hilo sumador");
        Restador restador = new Restador("Hilo restador");

        long start = System.currentTimeMillis();

        sumador.start();
        restador.start();




        try {
            // Con join
            //sumador.join();
            //restador.join();


            // Con espera activa con Thread.sleep
            //while(sumador.isAlive() || restador.isAlive()) {
            //    System.out.println("Sigo esperando a que terminen los hilos");
            //    Thread.sleep(250);
            //}

            // Con espera activa con join
            while(sumador.isAlive() || restador.isAlive()) {
                System.out.println("Sigo esperando a que terminen los hilos");
                // Ojo, porque si los dos están todavía vivos, se espera 500 en cada iteración
                sumador.join(250);
                restador.join(250);

            }




        } catch (InterruptedException e) {
            throw new RuntimeException("He sido interrumpido mientras esperaba.", e);
        }


        long stop = System.currentTimeMillis();

        long elapsed = stop - start;

        System.out.printf("Tiempo que han tardado: %d\n", elapsed);


    }

}
