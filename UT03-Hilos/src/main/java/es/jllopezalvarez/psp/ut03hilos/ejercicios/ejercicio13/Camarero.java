package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio13;

import net.datafaker.Faker;

public class Camarero extends Thread {
    private final Barra barra;
    private final long tiempoDescanso;

    public Camarero(String nombre, Barra barra, long tiempoDescanso) {
        super(nombre);
        this.barra = barra;
        this.tiempoDescanso = tiempoDescanso;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                System.out.printf("El camarero %s va a coger un plato de la barra.\n", this.getName());
                String plato = barra.cogerPlato();
                Thread.sleep(this.tiempoDescanso);
            }
            System.out.printf("El camarero %s ha terminado\n", this.getName());
        } catch (InterruptedException e){
            System.out.printf("El camarero %s se ha interrumpido\n", this.getName());
        }
    }
}
