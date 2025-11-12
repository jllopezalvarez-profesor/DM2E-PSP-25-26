package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo08prodconsum.ejemplo0801nosync;

import java.util.LinkedList;
import java.util.Queue;

public class NumberQueue {
    private final Queue<Integer> queue = new LinkedList<>();

    public void add(Integer number) {
        this.queue.add(number);
    }

    public Integer remove() {
        return this.queue.remove();
    }
}
