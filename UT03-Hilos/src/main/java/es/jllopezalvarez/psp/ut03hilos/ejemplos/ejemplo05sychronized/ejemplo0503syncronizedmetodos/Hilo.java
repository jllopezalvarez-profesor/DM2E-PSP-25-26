package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo05sychronized.ejemplo0503syncronizedmetodos;

public class Hilo extends Thread {

    private final int numSumas;
    private final long tiempoSiesta;
    private final Contador contador;

    public Hilo(String nombre, int numSumas, long tiempoSiesta, Contador contador) {
        super(nombre);
        this.numSumas = numSumas;
        this.tiempoSiesta = tiempoSiesta;
        this.contador = contador;
    }

    @Override
    public void run() {
        // Importante: siempre capturar InterruptedException si se hacen llamadas a métodos como
        // Thread.sleep, Thread.join, o cualquier otro que pueda poner el hilo en WAITING o TIMED_WAITING
        try {
            int i = numSumas;
            // También importante: usar Thread.interrupted (estático, resetea el flag de interrupción) o
            // Thread.isInterrupted (de instancia, no resetea el flag de interrupción) por si el hilo entra en un
            // estado blocked (al llegar a synchronized) o si se recibe la petición de interrupción cuando se está
            // ejecutando normalmente.
            while ((i > 0) && !this.isInterrupted()) {
                // Incrementar en uno el contador
                contador.incrementar();
                // Dormir el tiempo establecido
                Thread.sleep(tiempoSiesta);
                // Una iteración menos
                i--;
            }
            System.out.printf("Thread %s termina sin lanzamiento de InterruptedException.\n", getName());
        } catch (InterruptedException e) {
            System.out.printf("Thread %s interrumpida con lanzamiento de InterruptedException.\n", getName());
        }

    }
}
