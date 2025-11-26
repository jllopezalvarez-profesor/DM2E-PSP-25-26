package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio14;

import es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio13.Barra;

public class Main {
    private static final int NUM_CONSUMIDORES = 5;
    private static final int NUM_PRODUCTORES = 5;
    private static final long TIEMPO_ESPERA_PRODUCTORES = 500;
    private static final long TIEMPO_ESPERA_CONSUMIDORES = 1000;
    private static final long TIEMPO_TRABAJO_TOTAL = 5_000;
    private static final int TAMANIO_CINTA = 5;

    public static void main(String[] args) throws InterruptedException {
        CintaTransportadora cinta = new CintaTransportadora(TAMANIO_CINTA);

        List<Cocinero> cocineros = new ArrayList<>();
        List<Camarero> camareros = new ArrayList<>();

        // crear y lanzar cocineros
        for (int i = 0; i < NUM_PRODUCTORES; i++) {
            Cocinero c = new Cocinero("Cocinero " + i, barra, TIEMPO_ESPERA_PRODUCTORES);
            c.start();
            cocineros.add(c);
        }

        // crear y lanzar camareros
        for (int i = 0; i < NUM_CONSUMIDORES; i++) {
            Camarero c = new Camarero("Camarero " + i, barra, TIEMPO_ESPERA_CONSUMIDORES);
            c.start();
            camareros.add(c);
        }

        long tiempoTranscurrido = 0;
        long momentoInicio = System.currentTimeMillis();

        while (tiempoTranscurrido < TIEMPO_TRABAJO_TOTAL) {
            System.out.printf("Contenido de la barra: %s\n", barra.getPreparados());
            Thread.sleep(TIEMPO_MUESTREO);
            tiempoTranscurrido = System.currentTimeMillis() -  momentoInicio;
        }

        Thread.sleep(TIEMPO_TRABAJO_TOTAL);

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
