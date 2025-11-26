package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio14;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CintaTransportadora {
    private final Queue<Saco> queue;
    private final int maxPeso;
    private final int maxNumSacos;
    private final int maxNumSacosIguales;

    public CintaTransportadora(int maxPeso, int maxNumSacos, int maxNumSacosIguales) {
        this.queue = new LinkedList<>();
        this.maxPeso = maxPeso;
        this.maxNumSacos = maxNumSacos;
        this.maxNumSacosIguales = maxNumSacosIguales;
    }

    public synchronized void ponerSaco(Saco saco) throws InterruptedException {
        while (!puedoPonerSaco(saco)) {
            wait();
        }
        queue.add(saco);
        notifyAll();
    }

    private boolean puedoPonerSaco(Saco saco) {
        return this.queue.size() < maxNumSacos
                && this.queue.stream().filter(s -> s.getMaterial() == saco.getMaterial()).count() < maxNumSacosIguales
                && this.queue.stream().mapToInt(Saco::getPeso).sum() + saco.getPeso() < maxPeso;
    }

    public synchronized Saco cogerSaco() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Saco saco = queue.remove();
        notify();
        return saco;
    }


    public synchronized List<Saco> getSacos() {
        return List.copyOf(queue);
    }
}
