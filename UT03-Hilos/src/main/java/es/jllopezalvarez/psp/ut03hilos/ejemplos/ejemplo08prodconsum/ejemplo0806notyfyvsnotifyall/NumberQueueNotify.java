package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0806notyfyvsnotifyall;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberQueueNotify implements NumberQueue {
    private final Queue<Integer> queue;
    private final int maxSize;

    public NumberQueueNotify(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new LinkedList<>();
    }

    @Override
    public synchronized void add(Integer number) throws InterruptedException {

        // Añadir el elemento a la cola
        while (queue.size() >= maxSize) {
            wait();
        }

        this.queue.add(number);

        // Tras añadir, por si hay alguien esperando datos, se lanza un notify().
        // Podría usarse notifyAll, que despertaría a más de un hilo.
        // Si no hay otros hilos esperando, esta llamada no tiene consecuencias, no hace nada.
        // System.out.printf("Cola: Se ha añadido un número. Hilo %s informa al resto.\n", Thread.currentThread().getName());
        this.notify();

    }

    @Override
    public synchronized Integer remove() throws InterruptedException {

        // El hilo que ha pedido algo espera si no hay datos. Solo se despierta cuando otro hilo haga un notify.
        // Un if no basta, porque al despertarse puede no haber tampoco datos.
        while (this.queue.isEmpty()) {
            // Wait se puede interrumpir, cuando se interrumpe el hilo.
            // Se lanzará InterruptedException. Como interesa que esta excepción llegue al bucle del método run
            // del hilo, se declara que el método puede lanzar la excepción
            // System.out.printf("Cola: No hay nada en la cola. Hilo %s queda esperando.\n", Thread.currentThread().getName());
            this.wait();
        }

        // Obtener el elemento
        int number =  this.queue.remove();

        // Notificar
        // System.out.printf("Cola: Se ha sacado un número. Hilo %s informa al resto.\n", Thread.currentThread().getName());

        notify();

        return number;

    }

    @Override
    public synchronized List<Integer> getNumbers() {
        return List.copyOf(this.queue);
    }
}
