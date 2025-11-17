package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio13;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Barra {
    private final Queue<String> cola;
    private final int tamanioMaximo;

    public Barra(int tamanioMaximo) {
        this.cola = new LinkedList<>();
        this.tamanioMaximo = tamanioMaximo;
    }

    // synchronized es equivalente a synchronized(this)
    public synchronized void colocarPlato(String plato) throws InterruptedException {
        // Si no hay espacio esperar
        // Tiene que ser while
        if (this.cola.size() >= this.tamanioMaximo) {
            wait(); // this.wait();
        }
        this.cola.add(plato);
        // Avisar a los camareros de que hay plato
        notifyAll(); // this.notifyAll();
    }

    public synchronized String cogerPlato() throws InterruptedException {
        // Si no hay platos para servir el camarero esperará
        // Tiene que ser while
        if (cola.isEmpty()){
            wait();
        }

        String plato = cola.remove();

        // Avisar a los cocineros de que queda un espacio para plato.
        notifyAll();

        return plato;
    }

    public synchronized List<String> getPreparados() {
        return List.copyOf(this.cola);
    }

}
