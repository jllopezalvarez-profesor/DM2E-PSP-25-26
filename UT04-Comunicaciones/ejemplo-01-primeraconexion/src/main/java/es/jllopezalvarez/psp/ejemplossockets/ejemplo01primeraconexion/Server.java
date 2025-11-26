package es.jllopezalvarez.psp.ejemplossockets.ejemplo01primeraconexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("En el servidor antes de accept");
            Socket clientSocket = serverSocket.accept();
            System.out.println("En el servidor después de accept");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
