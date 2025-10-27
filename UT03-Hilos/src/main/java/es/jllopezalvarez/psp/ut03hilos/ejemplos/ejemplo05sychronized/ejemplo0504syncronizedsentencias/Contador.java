package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo05sychronized.ejemplo0504syncronizedsentencias;

public class Contador {
    // No es necesario usar volatile porque al modificarlo dentro de synchronized
    // se garantiza que se actualizará el valor en la memoria principal.
    // Además, las versiones modernas de Java garantizan la lectura coherente de ciertos tipos, int entre otros.
    private int contador = 0;

    // "Candado" usado para poder hacer el bloqueo cuando se usa synchronized como sentencia
    // Se hace final porque no se modifica el objeto, solo se usa para el bloqueo.
    private final Object candado = new Object();

    public int getContador() {
        return contador;
    }

    // Ahora estos dos métodos no son sincronizados, solo se sincroniza la sentencia que es necesario sincronizar.
    // Funciona exáctamente igual que los métodos sincronizados, pero hay que tener cuidado con usar el objeto adecuado
    // para el bloqueo. Si en una sentencia synchronized se usa un objeto distinto a los otros, no habrá exclusión mutua.
    public void incrementar() {
        // Aquí código que no es necesario sincronizar
        synchronized (candado) {
            contador++;
        }
        // Aquí más código que no es necesario sincronizar
    }

    public void decrementar() {
        // Aquí código que no es necesario sincronizar
        synchronized (candado) {
            contador--;
        }
        // Aquí más código que no es necesario sincronizar
    }


}
