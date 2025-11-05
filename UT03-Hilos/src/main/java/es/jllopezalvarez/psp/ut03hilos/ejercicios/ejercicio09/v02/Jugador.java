package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio09.v02;

import java.util.Random;

public class Jugador extends Thread {

    private static final long TIEMPO_ESPERA_JUGADAS = 1;
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
        try {
            while (!arbitro.juegoHaFinalizado() && !this.isInterrupted()) {
                if (arbitro.getTurnoSiguienteJugador() == this.numJugador) {
                    int intento = random.nextInt(maxNumSecreto) + 1;
                    arbitro.comprobarJugada(this.numJugador, intento);
                }
                // Simulamos un tiempo de espera entre jugadas
                Thread.sleep(TIEMPO_ESPERA_JUGADAS);
            }
        } catch (InterruptedException e) {

            System.out.printf("El jugador %d ha sido interrumpido con excepción.\n", numJugador);
        }
    }
}
