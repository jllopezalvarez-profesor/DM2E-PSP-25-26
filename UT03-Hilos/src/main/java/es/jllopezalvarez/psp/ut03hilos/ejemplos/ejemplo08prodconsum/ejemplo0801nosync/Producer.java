package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0801nosync;

import java.util.Random;

public class Producer extends Thread {
    private final long maxSleepTime;
    private NumberQueue queue;
    private static Random random = new Random();

    public Producer(String name, NumberQueue queue, long maxSleepTime) {
        super(name);
        this.queue = queue;
        this.maxSleepTime = maxSleepTime;
    }

    public void run() {
        System.out.printf("El productor %s inicia su trabajo.\n", this.getName());
        try {
            while (!this.isInterrupted()) {

                // Generar numero aleatorio entre 1 y 100
                int number = random.nextInt(100) + 1;

                // Añadirlo a la cola
                queue.add(number);

                // Mostrar mensaje
                System.out.printf("Productor %s: se ha añadido %d a la cola.\n", this.getName(), number);

                // El hilo se duerme el tiempo establecido
                Thread.sleep(random.nextLong(this.maxSleepTime));
            }
        } catch (InterruptedException e) {
            System.out.printf("El productor %s ha sido interrumpido.\n", this.getName());
        }
    }

}
