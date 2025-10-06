package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo01;


public class ProgramaPpal {

    public static void main(String[] args) throws InterruptedException {
        Hilo hilo = new Hilo();
        Hilo hilo2 = new Hilo();
        System.out.println("Iniciando programa...");
        // hilo.run(); // Esto no lanza en paralelo
        hilo.start();
        hilo2.start();

        hilo.join();
        hilo2.join();

        System.out.println("Finalizando programa...");


    }

}
