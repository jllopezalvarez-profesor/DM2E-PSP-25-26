package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo05sychronized.ejemplo0501sinsincronizar;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int NUM_HILOS = 5;
    private static final int NUM_SUMAS_POR_HILO = 500_000;
    private static final long TIEMPO_SIESTA = 0;

    public static void main(String[] args) throws InterruptedException {

        // Crear el contador, objeto que se compartirá entre todos los hilos
        Contador contadorCompartido = new Contador();

        // Crear estructura (lista) para almacenar los hilos
        List<Thread> hilos = new ArrayList<>();

        // Crear una serie de hilos que compartan el contador
        for (int i = 1; i <= NUM_HILOS; i++) {
            hilos.add(new Hilo("Hilo " + i, NUM_SUMAS_POR_HILO, TIEMPO_SIESTA, contadorCompartido));
        }

        // Arrancar los hilos
        for (Thread hilo : hilos){
            hilo.start();
        }

        // Esperar a que acaben
        for (Thread hilo : hilos){
            hilo.join();
        }

        // Verificar el resultado
        int resultadoEsperado = NUM_SUMAS_POR_HILO * NUM_HILOS;
        System.out.printf("Se esperaba %d y se ha obtenido %d\n", resultadoEsperado, contadorCompartido.getContador());
        System.out.printf("Diferencia: %d\n", resultadoEsperado - contadorCompartido.getContador());
    }
}
