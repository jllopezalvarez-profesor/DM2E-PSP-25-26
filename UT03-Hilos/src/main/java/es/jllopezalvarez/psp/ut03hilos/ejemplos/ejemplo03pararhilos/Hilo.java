package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo03pararhilos;

public class Hilo extends Thread {

    private final long millis;

    public Hilo(String name, long millis) {
        super(name);
        this.millis = millis;
    }
    @Override
    public void run() {

        try {
            while (!this.isInterrupted()) {

                System.out.printf("El hilo %s sigue corriendo\n",  this.getName());
                sleep(this.millis);
            }
            System.out.printf("El hilo %s se ha terminado por detectar isInterrupted\n", this.getName());

        } catch (InterruptedException e) {
            System.out.printf("El hilo %s se interrumpido con excepción\n",  this.getName());
        }

    }
}
