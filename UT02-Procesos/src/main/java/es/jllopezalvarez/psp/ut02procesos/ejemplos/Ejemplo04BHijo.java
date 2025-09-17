package es.jllopezalvarez.psp.ut02procesos.ejemplos;

/**
 * Programa lanzado como proceso hijo por el programa "Ejemplo04APadre"
 * Este programa se puede lanzar independientemente, para comprobar su funcionamiento, y luego poder
 * verificar la salida capturada por el programa padre.
 */
public class Ejemplo04BHijo {

    // Número de ciclos que realiza el programa
    private static final int LOOP_COUNT = 100;
    // Tiempo que se dormirá en cada ciclo. En milisegundos
    private static final long SLEEP_TIME_MILLISECONDS = 1000;

    public static void main(String[] args) {
        try {
            int loopNumber = 1;
            // Repetir el bucle tantas veces como se desee
            while (loopNumber <= LOOP_COUNT) {
                System.out.printf("En iteración %d. Duermo %d milisegundos.\n", loopNumber, SLEEP_TIME_MILLISECONDS);
                loopNumber++;
                // El proceso (el hilo) se duerme el tiempo establecido.
                Thread.sleep(SLEEP_TIME_MILLISECONDS);
            }
        } catch (InterruptedException e) {
            // El proceso (el hilo, en realidad) se ha interrumpido cuando estaba bloqueado o esperando.
            // En este caso concreto se ha interrumpido en la llamada a "sleep".
            throw new RuntimeException("El proceso ha sido interrumpido", e);
        }
    }
}
