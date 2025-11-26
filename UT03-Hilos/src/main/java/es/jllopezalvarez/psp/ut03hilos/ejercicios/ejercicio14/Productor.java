package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio14;

import java.util.Random;

public class Productor extends Thread {
    private final long tiempoMaximoDescanso;
    private final CintaTransportadora cintaTransportadora;
    private final Random random;

    public Productor(String nombre, long tiempoMaximoDescanso, CintaTransportadora cintaTransportadora) {
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

                cintaTransportadora.ponerSaco(generarSacoAleatorio());

            }
        } catch (InterruptedException e) {
            System.out.printf("%s ha sido interrumpido.\n", this.getName());
        }
    }

    private Saco generarSacoAleatorio() {
        return new Saco(
                Material.values()[random.nextInt(Material.values().length)],
                generarPesoAleatorio());
    }

    private int generarPesoAleatorio() {
        return switch (random.nextInt(3)){
            case 0 -> 50;
            case 1 -> 20;
            default -> 5;
        };
    }
}
