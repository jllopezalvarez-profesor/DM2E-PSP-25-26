package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo01;


public class ProgramaPpal {

    public static void main(String[] args) throws InterruptedException {
        Hilo hilo1 = new Hilo();
        Hilo hilo2 = new Hilo();
        Hilo hilo3 = new Hilo();
        System.out.println("Antes de lanzar el 1");
        // hilo1.run(); // Esto no lanza en paralelo
        hilo1.start();
        System.out.println("Entre el lanzamiento del 1 y el 2");
        hilo2.start();
        System.out.println("Entre el lanzamiento del 2 y el 3");
        hilo3.start();
        System.out.println("Tras finalizar el lanzamiento del 3");


        hilo1.join();
        hilo2.join();
        hilo3.join();

        System.out.println("Finalizando programa...");


    }

}
