package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio05;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 8000;
    private static final int MAX_CLIENTS = 10;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT, MAX_CLIENTS)){
            while (true) {
                // Atender a un cliente...
                System.out.println("Esperando conexión de cliente...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado. Creando hilo...");

                // Abrir un hilo para el nuevo cliente
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
                System.out.println("Hilo para el nuevo cliente lanzado");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
