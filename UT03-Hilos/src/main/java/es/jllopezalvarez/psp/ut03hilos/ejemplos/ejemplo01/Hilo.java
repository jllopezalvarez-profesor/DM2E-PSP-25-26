package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo01;

public class Hilo extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(i + " - " + this.threadId());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Me paro porque me han interrumpido");
                return;
            }
        }

    }
}
