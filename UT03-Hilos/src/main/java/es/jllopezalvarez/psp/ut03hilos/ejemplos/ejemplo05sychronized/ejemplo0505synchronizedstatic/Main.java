package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo05sychronized.ejemplo0505synchronizedstatic;

public class Main {

    private static final int NUM_SUMAS_POR_HILO = 1_000_000;
    private static final long TIEMPO_SIESTA = 0;

    public static void main(String[] args) throws InterruptedException {

        Contador contadorCompartido = new Contador();

        Hilo hilo1 = new Hilo("Hilo 1", NUM_SUMAS_POR_HILO, TIEMPO_SIESTA, contadorCompartido);
        Hilo hilo2 = new Hilo("Hilo 2", NUM_SUMAS_POR_HILO, TIEMPO_SIESTA, contadorCompartido);
        Hilo hilo3 = new Hilo("Hilo 3", NUM_SUMAS_POR_HILO, TIEMPO_SIESTA, contadorCompartido);

        // Arrancar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Esperar a que acaben
        hilo1.join();
        hilo2.join();
        hilo3.join();

        // Verificar el resultado
        int resultadoEsperado = NUM_SUMAS_POR_HILO * 3;
        System.out.printf("Se esperaba %d y se ha obtenido %d\n", resultadoEsperado, contadorCompartido.getContador());
        System.out.printf("Se esperaba %d en el estático y se ha obtenido %d\n", resultadoEsperado, Contador.getContadorEstatico());


    }
}
