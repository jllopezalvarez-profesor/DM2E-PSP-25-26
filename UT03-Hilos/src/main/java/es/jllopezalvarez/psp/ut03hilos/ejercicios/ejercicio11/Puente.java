package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio11;

import java.util.concurrent.Semaphore;

public class Puente {
    Semaphore semaforo = new Semaphore(1);

    public void cruzar(String matricula) throws InterruptedException {
        try{
            System.out.printf("El coche con matricula %s quiere cruzar.\n", matricula);
            semaforo.acquire();
            System.out.printf("El coche con matricula %s está cruzando.\n",  matricula);
            Thread.sleep(1000);
            System.out.printf("El coche con matricula %s ya ha cruzado.\n",  matricula);
        } finally {
            semaforo.release();
        }
    }
}
