package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio13;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int NUM_CAMAREROS = 5;
    private static final int NUM_COCINEROS = 5;
    private static final long TIEMPO_ESPERA_COCINEROS = 500;
    private static final long TIEMPO_ESPERA_CAMAREROS = 1000;
    private static final long TIEMPO_COCINADO_TOTAL = 5_000;
    private static final long TIEMPO_MUESTREO = 500;
    private static final int TAMANIO_BARRA = 5;

    public static void main(String[] args) throws InterruptedException {
        Barra barra = new Barra(TAMANIO_BARRA);

        List<Cocinero> cocineros = new ArrayList<>();
        List<Camarero> camareros = new ArrayList<>();

        // crear y lanzar cocineros
        for (int i = 0; i < NUM_COCINEROS; i++) {
            Cocinero c = new Cocinero("Cocinero " + i, barra, TIEMPO_ESPERA_COCINEROS);
            c.start();
            cocineros.add(c);
        }

        // crear y lanzar camareros
        for (int i = 0; i < NUM_CAMAREROS; i++) {
            Camarero c = new Camarero("Camarero " + i, barra, TIEMPO_ESPERA_CAMAREROS);
            c.start();
            camareros.add(c);
        }

        long tiempoTranscurrido = 0;
        long momentoInicio = System.currentTimeMillis();

        while (tiempoTranscurrido < TIEMPO_COCINADO_TOTAL) {
            System.out.printf("Contenido de la barra: %s\n", barra.getPreparados());
            Thread.sleep(TIEMPO_MUESTREO);
            tiempoTranscurrido = System.currentTimeMillis() -  momentoInicio;
        }

        Thread.sleep(TIEMPO_COCINADO_TOTAL);

        // Parar los cocineros
        for (Cocinero c : cocineros) {
            c.interrupt();
        }
        for (Cocinero c : cocineros) {
            c.join();
        }

        // Esperar a que no queden platos
        while (!barra.getPreparados().isEmpty()){
            Thread.sleep(TIEMPO_MUESTREO);
        }

        for (Camarero c : camareros) {
            c.interrupt();
        }
        for (Camarero c : camareros) {
            c.join();
        }




    }
}
