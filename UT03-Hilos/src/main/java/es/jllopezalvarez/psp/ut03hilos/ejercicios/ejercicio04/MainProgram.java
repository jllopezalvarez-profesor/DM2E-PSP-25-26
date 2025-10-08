package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio04;

public class MainProgram {
    public static void main(String[] args) throws InterruptedException {
        MessageThread messageThread1 = new MessageThread("Hilo 1", 20, 100);
        MessageThread messageThread2 = new MessageThread("Hilo 2", 30, 100);

        Thread thread1 = new Thread(messageThread1);
        Thread thread2 = new Thread(messageThread2);

        thread1.start();
        thread2.start();

        thread1.join();

        System.out.println("Ya ha terminado el hilo 1 y yo termino también");


    }





}
