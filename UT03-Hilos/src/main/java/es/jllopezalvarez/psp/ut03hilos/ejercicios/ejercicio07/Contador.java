package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio07;

public class Contador implements Runnable {

    private String nombreContador;
    private long tiempoDeSiesta;

    private int valorActual = 0;

    public Contador(String nombreContador, long tiempoDeSiesta) {
        this.nombreContador = nombreContador;
        this.tiempoDeSiesta = tiempoDeSiesta;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.printf("Soy '%s' y voy por el %d.\n", nombreContador, valorActual);
                valorActual += 1;
                Thread.sleep(tiempoDeSiesta);
            }
            System.out.printf("Contador %s terminado normalmente\n", nombreContador);
        } catch (InterruptedException e) {
            System.out.printf("Contador %s terminado por excepción Interruptedexception\n", nombreContador);
        } finally {
            // Liberar recursos reservados en el try
        }

    }
}
