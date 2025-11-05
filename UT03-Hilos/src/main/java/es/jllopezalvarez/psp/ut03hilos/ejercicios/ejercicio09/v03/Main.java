package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio09.v03;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final int NUM_JUGADORES = 1000;
    public static final int MAX_NUM_ADIVINAR = 25;

    public static void main(String[] args) throws InterruptedException {
        // Indicar inicio del programa
        System.out.println("Inicio del programa.");

        // Crear el árbitro, que se comparte entre todos los jugadores.
        Arbitro arbitro = new Arbitro(NUM_JUGADORES, MAX_NUM_ADIVINAR);

        // Lista para guardar los jugadores.
        List<Jugador> jugadores = new ArrayList<>();

        // Crear jugadores
        for (int i = 0; i < NUM_JUGADORES; i++) {
            jugadores.add(new Jugador(i+1, arbitro, MAX_NUM_ADIVINAR));
        }

        // Arrancar los jugadores
        jugadores.forEach(Thread::start);

        // Esperar a los jugadores
        for (Jugador jugador : jugadores) {
            jugador.join();
        }

        // Indicar fin del programa
        System.out.println("Fin del programa.");
    }
}
