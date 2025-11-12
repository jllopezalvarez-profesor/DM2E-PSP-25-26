package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0805syncwaitnotifylimitado;

import java.util.LinkedList;
import java.util.Queue;

public class NumberQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    public NumberQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void add(Integer number) throws InterruptedException {

        // Mientras que la cola esté llena, se haya alcanzado la máxima capacidad, esperar
        while (this.queue.size() >= this.capacity) {
            // Wait se puede interrumpir, cuando se interrumpe el hilo.
            // Se lanzará InterruptedException. Como interesa que esta excepción llegue al bucle del método run
            // del hilo, se declara que el método puede lanzar la excepción
            System.out.printf("Cola: No hay sitio en la cola. Hilo %s queda esperando.\n", Thread.currentThread().getName());
            this.wait();
        }

        // Añadir el elemento a la cola, ahora que hay sitio
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

        // Obtener el elemento.
        // No se puede hacer directamente return. Tras sacar el elemento hay que avisar a posibles hilos esperando.
        int value = this.queue.remove();

        // Tras sacar el elemento de la cola, ya que ha quedado espacio, por si hay un productor esperando para
        // colocar un número, hacer notify()
        System.out.printf("Cola: Se ha hecho sitio en la cola. Hilo %s informa al resto.\n", Thread.currentThread().getName());
        this.notify();

        // Una vez notificado, devolver el valor
        return value;
    }

    // Ya no es necesario en esta versión, porque el productor y consumidor no necesitan saber si la cola está vacía
    // public synchronized boolean isEmpty() {
    //     return this.queue.isEmpty();
    // }
}
