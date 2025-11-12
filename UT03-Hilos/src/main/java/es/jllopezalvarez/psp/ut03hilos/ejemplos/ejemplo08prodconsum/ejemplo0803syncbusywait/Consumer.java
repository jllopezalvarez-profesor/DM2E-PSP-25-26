package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0803syncbusywait;

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

                // Espera activa: mientras no haya nada en la cola, se espera
                while (queue.isEmpty()) {
                    System.out.printf("Consumidor %s: la cola está vacía. Espero un poco.\n", this.getName());
                    sleep(random.nextLong(this.maxSleepTime));
                }

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
