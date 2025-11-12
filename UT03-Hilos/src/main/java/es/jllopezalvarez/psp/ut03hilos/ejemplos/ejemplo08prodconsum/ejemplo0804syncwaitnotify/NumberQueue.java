package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0804syncwaitnotify;

import java.util.LinkedList;
import java.util.Queue;

public class NumberQueue {
    private final Queue<Integer> queue = new LinkedList<>();

    public synchronized void add(Integer number) {

        // Añadir el elemento a la cola
        this.queue.add(number);

        // Tras añadir, por si hay alguien esperando datos, se lanza un notify().
        // Podría usarse notifyAll, que despertaría a más de un hilo.
        // Si no hay otros hilos esperando, esta llamada no tiene consecuencias, no hace nada.
        System.out.printf("Cola: Se ha añadido un número. Hilo %s informa al resto.\n", Thread.currentThread().getName());
        this.notify();

    }

    public synchronized Integer remove() throws InterruptedException {

        // El hilo que ha pedido algo espera si no hay datos. Solo se despierta cuando otro hilo haga un notify.
        // Un if no basta, porque al despertarse puede no haber tampoco datos.
        while (this.queue.isEmpty()) {
            // Wait se puede interrumpir, cuando se interrumpe el hilo.
            // Se lanzará InterruptedException. Como interesa que esta excepción llegue al bucle del método run
            // del hilo, se declara que el método puede lanzar la excepción
            System.out.printf("Cola: No hay nada en la cola. Hilo %s queda esperando.\n", Thread.currentThread().getName());
            this.wait();
        }

        // Obtener el elemento
        return this.queue.remove();
    }

    // Ya no es necesario en esta versión, porque el productor y consumidor no necesitan saber si la cola está vacía
    // public synchronized boolean isEmpty() {
    //     return this.queue.isEmpty();
    // }
}
