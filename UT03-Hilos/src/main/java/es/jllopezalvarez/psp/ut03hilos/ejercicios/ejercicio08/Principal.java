package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio08;

public class Principal {

    public static void main(String[] args) throws InterruptedException {
        Thread th1 = new Thread( new Contador("contador 1", 300));
        Thread th2 = new Thread( new Contador("contador 2", 400));
        Thread th3 = new Thread( new Contador("contador 3", 500));
        Thread th4 = new Thread( new Contador("contador 4", 600));
        Thread th5 = new Thread( new Contador("contador 5", 700));

        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();



        th1.interrupt();
        Thread.sleep(500);
        th2.interrupt();
        Thread.sleep(500);
        th3.interrupt();
        Thread.sleep(500);
        th4.interrupt();
        Thread.sleep(500);
        th5.interrupt();
        Thread.sleep(500);

        Thread.sleep(500);


        th1.join();
        th2.join();
        th3.join();
        th4.join();
        th5.join();

        System.out.println("Programa principal terminado");
    }
}
