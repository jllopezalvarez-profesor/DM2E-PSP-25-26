package es.jllopezalvarez.psp.ut02procesos.ejemplos;

/**
 * Programa lanzado como proceso hijo por el programa "Ejemplo05APadre"
 * Este programa se puede lanzar independientemente, para comprobar su funcionamiento, y luego poder
 * verificar que la salida se ha volcado correctamente a un fichero indicado por el programa padre.
 */
public class Ejemplo05BHijo {

    // Número de ciclos que realiza el programa
    private static final int LOOP_COUNT = 10;
    // Tiempo que se dormirá en cada ciclo. En milisegundos
    private static final long SLEEP_TIME_MILLISECONDS = 500;

    public static void main(String[] args) {
        // Escribir algo en stderr para poder capturarlo
        System.err.println("Escribiendo en el stream de errores estándar para comprobar que funciona sin tener que matar el proceso");
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
