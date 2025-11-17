package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio13;

import net.datafaker.Faker;

public class Cocinero extends Thread {
    private final Barra barra;
    private final long tiempoDescanso;
    private final Faker faker;

    public Cocinero(String nombre, Barra barra, long tiempoDescanso) {
        super(nombre);
        this.barra = barra;
        this.tiempoDescanso = tiempoDescanso;
        faker = new Faker();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                String plato = faker.food().dish();
                System.out.printf("El cocinero %s va a colocar el plato %s en la barra.\n", this.getName(), plato);
                barra.colocarPlato(plato);
                Thread.sleep(this.tiempoDescanso);
            }
            System.out.printf("El cocinero %s ha terminado\n", this.getName());
        } catch (InterruptedException e){
            System.out.printf("El cocinero %s se ha interrumpido\n", this.getName());
        }
    }
}
