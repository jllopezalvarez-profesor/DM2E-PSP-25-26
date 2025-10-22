package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo05sychronized.ejemplo0501;

public class Hilo extends Thread {

    private final int numSumas;
    private final long tiempoSiesta;
    private final Contador contador;

    public Hilo(String nombre, int numSumas, long tiempoSiesta, Contador contador) {
        super(nombre);
        this.numSumas = numSumas;
        this.tiempoSiesta = tiempoSiesta;
        this.contador = contador;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while ((i < numSumas) && !this.isInterrupted()) {
                contador.incrementar();
                Thread.sleep(tiempoSiesta);
                i++;
            }
            System.out.printf("Thread %s termina sin excepción.\n", getName());
        } catch (InterruptedException e) {
            System.out.printf("Thread %s interrumpida con excepción.\n", getName());
        }

    }
}
