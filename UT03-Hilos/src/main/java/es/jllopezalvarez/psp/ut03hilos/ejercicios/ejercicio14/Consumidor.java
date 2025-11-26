package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio14;

import java.util.Random;

public class Consumidor extends Thread {
    private final long tiempoMaximoDescanso;
    private final CintaTransportadora cintaTransportadora;
    private final Random random;

    public Consumidor(String nombre, long tiempoMaximoDescanso, CintaTransportadora cintaTransportadora) {
        super(nombre);
        this.tiempoMaximoDescanso = tiempoMaximoDescanso;
        this.cintaTransportadora = cintaTransportadora;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Thread.sleep(random.nextLong(tiempoMaximoDescanso));

                cintaTransportadora.cogerSaco();

            }
        } catch (InterruptedException e) {
            System.out.printf("%s ha sido interrumpido.\n", this.getName());
        }
    }

}
