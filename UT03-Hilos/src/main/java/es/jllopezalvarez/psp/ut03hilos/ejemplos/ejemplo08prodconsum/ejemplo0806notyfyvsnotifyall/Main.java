package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0806notyfyvsnotifyall;


import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final long CONSUMER_MAX_SLEEP_TIME = 2000;
    private static final long PRODUCER_MAX_SLEEP_TIME = 10;
    private static final long PROGRAM_RUN_TIME = 1000 * 5;
    public static final int ACTIVE_WAIT_TIME = 500;
    public static final int NUMBER_OF_CONSUMERS = 100;
    public static final int MAX_SIZE = 100;

    public static void main(String[] args) throws InterruptedException {

        // Inicio del programa
        System.out.println("Programa iniciado.");

        // Crear cola
        // NumberQueue queue = new NumberQueueNotify(MAX_SIZE);
        NumberQueue queue = new NumberQueueNotifyAll(MAX_SIZE);

        // Crear y arrancar consumidores.
        List<Consumer> consumers = new ArrayList<Consumer>(NUMBER_OF_CONSUMERS);
        for (int i = 0; i < NUMBER_OF_CONSUMERS; i++) {
            Consumer c = new Consumer("Consumidor " + (i+1), queue, CONSUMER_MAX_SLEEP_TIME);
            c.start();
            consumers.add(c);
        }

        // Crear y arrancar productor
        Producer producer = new Producer("Productor", queue, PRODUCER_MAX_SLEEP_TIME);
        producer.start();

        // Esperar el tiempo indicado en el programa
        Thread.sleep(PROGRAM_RUN_TIME);

        // Finalizar (interrumpir) el productor, y esperar a que acabe
        producer.interrupt();
        producer.join();

        long startMillis = System.currentTimeMillis();

        // Espera activa hasta que acaben los consumidores
        while (!queue.getNumbers().isEmpty()){
            // Esperamos medio segundo
            Thread.sleep(ACTIVE_WAIT_TIME);
        }

        long endMillis = System.currentTimeMillis();

        // Finalizar y esperar a que acaben los consumidores
        for (Consumer c : consumers) {
            c.interrupt();
        }
        for (Consumer c : consumers) {
            c.join();
        }

        // Fin del programa
        System.out.printf("Programa finalizado. Tiempo invertido %f.\n", ((double)(endMillis - startMillis))/1000);
    }
}
