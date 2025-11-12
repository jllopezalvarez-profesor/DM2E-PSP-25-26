package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0802sync;

public class Main {
    private static final long CONSUMER_MAX_SLEEP_TIME = 1000;
    private static final long PRODUCER_MAX_SLEEP_TIME = 1000;
    private static final long PROGRAM_RUN_TIME = 1000 * 15;


    public static void main(String[] args) throws InterruptedException {

        // Inicio del programa
        System.out.println("Programa iniciado.");

        // Crear cola
        NumberQueue queue = new NumberQueue();

        // Crear y arrancar productor
        Producer producer = new Producer("Productor 1", queue, PRODUCER_MAX_SLEEP_TIME);
        producer.start();

        // Crear y arrancar consumidor
        Consumer consumer = new Consumer("Consumidor 1", queue, CONSUMER_MAX_SLEEP_TIME);
        consumer.start();

        // Esperar el tiempo indicado en el programa
        Thread.sleep(PROGRAM_RUN_TIME);

        // Finalizar (interrumpir) los hilos
        producer.interrupt();
        consumer.interrupt();

        // Esperar que acaben los hilos tras interrumpirlos
        producer.join();
        consumer.join();

        // Fin del programa
        System.out.println("Programa finalizado.");
    }
}
