package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio11;



public class Main {
    private static final int NUM_COCHES = 10;

    public static void main(String[] args) {
        Puente puente = new Puente();

        for (int i = 0; i < NUM_COCHES; i++) {
            Thread thread = new Thread(new Coche("matricula"+i, puente));
            thread.start();
        }
    }

}
