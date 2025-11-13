package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo07waitnotify;

public class Ejemplo0702NoTermina {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Inicio del programa...");

        // Crear un objeto para usarlo como "candado"
        Object object = new Object();

        // Se abre una zona sincronizada usando el objeto creado como candado.
        // Marca un warning con "Synchronization on local variable..."
        // Esto es porque la sincronización debe hacerse sobre objetos compartidos, y este object no lo está.
        synchronized (object) {
            // No lanza excepción ahora, pero...
            // como nadie hace object.notify, el programa no termina nunca
            object.wait();

        }

        System.out.println("Fin del programa...");
    }


}
