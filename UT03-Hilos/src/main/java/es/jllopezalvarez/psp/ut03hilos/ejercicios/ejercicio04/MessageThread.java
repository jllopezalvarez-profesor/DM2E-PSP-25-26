package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio04;

public class MessageThread implements Runnable {

    private final String name;
    private final int loopCount;
    private final long sleepTime;

    public MessageThread(String name, int loopCount, long sleepTime) {
        this.name = name;
        this.loopCount = loopCount;
        this.sleepTime = sleepTime;
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < loopCount; i++) {
                System.out.printf("Soy %s y voy por la iteración %d\n", name, i);
                Thread.sleep(sleepTime);
            }
            System.out.printf("Soy %s y ya he terminado\n", name);
        } catch (InterruptedException e) {
            throw new RuntimeException("He sido interrumpido", e);
        }
    }
}


