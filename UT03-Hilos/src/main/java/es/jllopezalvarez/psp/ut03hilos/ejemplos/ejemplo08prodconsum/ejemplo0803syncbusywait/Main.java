package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0803syncbusywait;

public class Main {
    private static final long CONSUMER_MAX_SLEEP_TIME = 1000;
    private static final long PRODUCER_MAX_SLEEP_TIME = 3000;
    private static final long PROGRAM_RUN_TIME = 1000 * 15;


    public static void main(String[] args) throws InterruptedException {

        // Inicio del programa
        System.out.println("Programa iniciado.");

        // Crear cola
        NumberQueue queue = new NumberQueue();

        // Crear y arrancar consumidor.
        // Como hay espera activa en el consumidor, se puede arrancar antes, porque espera a que haya algo en la cola.
        Consumer consumer = new Consumer("Consumidor 1", queue, CONSUMER_MAX_SLEEP_TIME);
        consumer.start();
        Consumer consumer2 = new Consumer("Consumidor 2", queue, CONSUMER_MAX_SLEEP_TIME);
        consumer2.start();
        Consumer consumer3 = new Consumer("Consumidor 3", queue, CONSUMER_MAX_SLEEP_TIME);
        consumer3.start();



        // Crear y arrancar productor
        Producer producer = new Producer("Productor 1", queue, PRODUCER_MAX_SLEEP_TIME);
        producer.start();

        // Esperar el tiempo indicado en el programa
        Thread.sleep(PROGRAM_RUN_TIME);

        // Finalizar (interrumpir) los hilos
        producer.interrupt();
        consumer.interrupt();
        consumer2.interrupt();
        consumer3.interrupt();

        // Esperar que acaben los hilos tras interrumpirlos
        producer.join();
        consumer.join();
        consumer2.join();
        consumer3.join();

        // Fin del programa
        System.out.println("Programa finalizado.");
    }
}
