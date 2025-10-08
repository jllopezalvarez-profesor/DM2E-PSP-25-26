package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo01;

import java.util.Random;

public class Hilo extends Thread {

    private static final Random RANDOM = new Random();

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(i + " - " + this.threadId());
            try {
                Thread.sleep(RANDOM.nextInt(300));
            } catch (InterruptedException e) {
                System.out.println("Me paro porque me han interrumpido");
                return;
            }
        }

    }
}
