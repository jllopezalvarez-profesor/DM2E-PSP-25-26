package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        // Crear el server socket y esperar la conexión del cliente
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
             Socket clientSocket = serverSocket.accept()) {

            System.out.println(clientSocket.getInetAddress().getHostAddress());
            System.out.println(clientSocket.getPort());
            System.out.println(clientSocket.getRemoteSocketAddress());
            System.out.println(clientSocket.getLocalSocketAddress());



        } catch (IOException e) {
            throw new RuntimeException("Error de E/S al abrir el socket en el servidor.", e);
        }
    }


}
