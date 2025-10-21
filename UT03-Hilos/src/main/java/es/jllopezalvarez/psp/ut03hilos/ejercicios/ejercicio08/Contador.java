package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio08;

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
                incrementarCuenta();
            }
            System.out.printf("Contador %s terminado normalmente\n", nombreContador);
        } catch (InterruptedException e) {
            System.out.printf("Contador %s terminado por excepción Interruptedexception\n", nombreContador);
        } finally {
            // Liberar recursos reservados en el try
        }

    }

    private void incrementarCuenta() throws InterruptedException {
        System.out.printf("Soy '%s' y voy por el %d.\n", nombreContador, valorActual);
        valorActual += 1;
        try {
            Thread.sleep(tiempoDeSiesta);
        } catch (InterruptedException e) {
            System.out.println("Excepción dentro del método 'interior'");
            throw new InterruptedException("Excepción relanzada");
        }
    }
}
