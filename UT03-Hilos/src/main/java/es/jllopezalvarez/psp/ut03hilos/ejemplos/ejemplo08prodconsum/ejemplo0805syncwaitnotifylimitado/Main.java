package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0805syncwaitnotifylimitado;

public class Main {
    private static final long CONSUMER_MAX_SLEEP_TIME = 2000;
    private static final long PRODUCER_MAX_SLEEP_TIME = 1000;
    private static final long PROGRAM_RUN_TIME = 1000 * 15;
    private static final int QUEUE_SIZE = 5;


    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();

        // Inicio del programa
        System.out.println("Programa iniciado.");

        // Crear cola
        NumberQueue queue = new NumberQueue(QUEUE_SIZE);

        // Crear y arrancar consumidor.
        // Como hay espera activa en el consumidor, se puede arrancar antes, porque espera a que haya algo en la cola.
        Consumer consumer = new Consumer("Consumidor 1", queue, CONSUMER_MAX_SLEEP_TIME);
        consumer.start();

        // Crear y arrancar productor
        Producer producer = new Producer("Productor 1", queue, PRODUCER_MAX_SLEEP_TIME);
        producer.start();

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
