package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo06semaphore;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int NUM_USUARIOS = 10;
    private static final int NUM_DOCS_POR_USUARIO = 5;
    private static final int NUM_IMPRESORAS = 2;

    public static void main(String[] args) throws InterruptedException {
        // Inicio del programa
        System.out.println("Fin del programa.");

        // Crear la reprografía
        Reprografia reprografia = new Reprografia(NUM_IMPRESORAS);

        // Crear los hilos usuario
        List<Usuario> usuarios = new ArrayList<>(NUM_USUARIOS);
        for (int i = 0; i < NUM_USUARIOS; i++) {
            usuarios.add(new Usuario("Usuario " + i, NUM_DOCS_POR_USUARIO, reprografia));
        }

        // Lanzar los usuarios
        for(Usuario usuario : usuarios) {
            usuario.start();
        }

        // Esperar a que terminen
        for(Usuario usuario : usuarios) {
            usuario.join();
        }

        // Fin del programa
        System.out.println("Fin del programa.");
    }
}
