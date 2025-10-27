package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo05sychronized.ejemplo0503syncronizedmetodos;

public class Contador {
    // No es necesario usar volatile porque al modificarlo dentro de synchronized
    // se garantiza que se actualizará el valor en la memoria principal.
    // Además, las versiones modernas de Java garantizan la lectura coherente de ciertos tipos, int entre otros.
    private int contador = 0;

    public int getContador() {
        return contador;
    }

    // Declarar estos dos métodos (incrementar y decrementar) como sincronizado implica que solo un hilo puede
    // ejecutarlo cualquiera de ellos a la vez. Si, mientras un hilo está ejecutando uno, otro intenta ejecutar el
    // mismo u otros métodos sincronizados, el segundo se queda bloqueado hasta que el hilo que estaba "dentro" termina.
    public synchronized void incrementar() {
        contador++;
    }

    public synchronized void decrementar() {
        contador--;
    }


}
