package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio09.v01;

import java.util.Random;

public class Arbitro {
    private final int numJugadores;
    private final int numeroSecreto;
    private int turnoSiguienteJugador;
    private boolean juegoFinalizado;

    public Arbitro(int numJugadores, int maxNumSecreto) {
        this.numJugadores = numJugadores;
        this.numeroSecreto = new Random().nextInt(maxNumSecreto) + 1;
        this.turnoSiguienteJugador = 0;
        this.juegoFinalizado = false;

        System.out.printf("El número que hay que adivinar es el %s.\n", numeroSecreto);
    }

    public int getTurnoSiguienteJugador() {
        // Se devuelve +1 porque se guarda el turno desde 0 hasta numJugadores-1
        // Para poder operar con aritmética modular.
        return turnoSiguienteJugador + 1;
    }

    public boolean juegoHaFinalizado() {
        return juegoFinalizado;
    }

    public void comprobarJugada(int numJugadorActual, int numero) {
        System.out.printf("El jugador %d dice %d. ", numJugadorActual, numero);
        if (numero == this.numeroSecreto) {
            System.out.printf("El jugador %d ha ganado el juego.\n", numJugadorActual);
            this.juegoFinalizado = true;
        } else {
            System.out.print("No es correcto. ");
            turnoSiguienteJugador = (turnoSiguienteJugador + 1) % numJugadores;
            System.out.printf("Siguiente jugador: %d.\n", this.getTurnoSiguienteJugador());
        }
    }
}
