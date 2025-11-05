package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo05sychronized.ejemplo0505synchronizedstatic;

public class Contador {
    private static int contadorEstatico = 0;

    private int contador = 0;

    public synchronized int getContador() {
        return contador;
    }

    public synchronized int getContadorConBloqueSincronizado() {
        return contador;
    }



    public synchronized void incrementar() {
        contador++;
    }

    public synchronized void decrementar() {
        contador--;
    }

    synchronized public static void incrementarEstatico(){
        contadorEstatico++;
    }

    public static synchronized void decrementarEstatico(){
        contadorEstatico--;
    }

    public static  void decrementarEstaticoEnBloque(){
        synchronized(Contador.class) {
            contadorEstatico--;
        }
    }

    public synchronized static int getContadorEstatico() {
        return contadorEstatico;
    }

}
