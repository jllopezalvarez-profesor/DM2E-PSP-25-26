package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0804syncwaitnotify;

import java.util.Random;

public class Consumer extends Thread {
    private final long maxSleepTime;
    private NumberQueue queue;
    private static Random random = new Random();

    public Consumer(String name, NumberQueue queue, long maxSleepTime) {
        super(name);
        this.queue = queue;
        this.maxSleepTime = maxSleepTime;
    }

    public void run() {
        System.out.printf("El consumidor %s inicia su trabajo.\n",  this.getName());
        try {
            while (!this.isInterrupted()) {

                // Obtener el primero en la cola
                int number = queue.remove();

                // Mostrar el mensaje
                System.out.printf("Consumidor %s: el cuadrado de %d es %d.\n", this.getName(), number, number*number);

                // El hilo se duerme el tiempo establecido
                Thread.sleep(random.nextLong(this.maxSleepTime));
            }
        } catch (InterruptedException e) {
            System.out.printf("El consumidor %s ha sido interrumpido.\n",   this.getName());
        }
    }

}
