package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio09.v03;

import java.util.Random;

public class Arbitro {
    private static final long TIEMPO_PENSAR = 200;
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

    public synchronized int getTurnoSiguienteJugador() {
        // Se devuelve +1 porque se guarda el turno desde 0 hasta numJugadores-1
        // Para poder operar con aritmética modular.
        return turnoSiguienteJugador + 1;
    }

    public synchronized boolean juegoHaFinalizado() {
        return juegoFinalizado;
    }

    public synchronized void comprobarJugada(int numJugadorActual, int numero) throws InterruptedException {
        System.out.printf("El jugador %d dice %d. ", numJugadorActual, numero);
        if (numero == this.numeroSecreto) {
            System.out.printf("El jugador %d ha ganado el juego.\n", numJugadorActual);
            this.juegoFinalizado = true;
        } else {
            System.out.print("No es correcto. ");
            // se separa la operación de cálculo de turno para poder demorarla y que otros hilos se "cuelen"
            int turnoSiguiente = (turnoSiguienteJugador + 1) % numJugadores;
            // Sleep que simula que el árbitro piensa un tiempo, para dar opciones a que haya problemas de sincronización.
            // No se controla la excepción (se declara), porque la deben controlar los hilos jugadores al realizar las jugadas.
            Thread.sleep(TIEMPO_PENSAR); // <- Otro hilo puede entrar en este punto y leer valores no actualizados
            turnoSiguienteJugador = turnoSiguiente;
            System.out.printf("Siguiente jugador: %d.\n", this.getTurnoSiguienteJugador());
        }
    }
}
