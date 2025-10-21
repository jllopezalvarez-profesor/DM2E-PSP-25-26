package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo02demonios;

public class Hilo extends Thread {

    public Hilo(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.printf("El hilo %s sigue corriendo\n",  this.getName());

                Thread.sleep(1000);

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
