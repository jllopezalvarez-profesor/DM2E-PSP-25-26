package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio09.v01;

import java.util.Random;

public class Jugador extends Thread {

    private final int numJugador;
    private final Arbitro arbitro;
    private final int maxNumSecreto;

    public Jugador(int numJugador, Arbitro arbitro, int maxNumSecreto) {
        this.numJugador = numJugador;
        this.arbitro = arbitro;
        this.maxNumSecreto = maxNumSecreto;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!arbitro.juegoHaFinalizado() && !this.isInterrupted()) {
            if (arbitro.getTurnoSiguienteJugador() == this.numJugador) {
                int intento = random.nextInt(maxNumSecreto) + 1;
                arbitro.comprobarJugada(this.numJugador, intento);
            }
        }
    }
}
