package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0802sync;

import java.util.LinkedList;
import java.util.Queue;

public class NumberQueue {
    private final Queue<Integer> queue = new LinkedList<>();

    public synchronized void add(Integer number) {
        this.queue.add(number);
    }

    public synchronized Integer remove() {
        return this.queue.remove();
    }
}
