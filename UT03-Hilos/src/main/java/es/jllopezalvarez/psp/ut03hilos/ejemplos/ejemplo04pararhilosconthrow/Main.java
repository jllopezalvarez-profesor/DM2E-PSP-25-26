package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo04pararhilosconthrow;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Hilo hilo1 = new Hilo("Hilo 1", 0);
        Hilo hilo2 = new Hilo("Hilo 2", 300);
        Hilo hilo3 = new Hilo("Hilo 3", 1000);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        Thread.sleep(2000);

        hilo1.interrupt();
        hilo2.interrupt();
        hilo3.interrupt();

        hilo1.join();
        hilo2.join();
        hilo3.join();

    }
}
