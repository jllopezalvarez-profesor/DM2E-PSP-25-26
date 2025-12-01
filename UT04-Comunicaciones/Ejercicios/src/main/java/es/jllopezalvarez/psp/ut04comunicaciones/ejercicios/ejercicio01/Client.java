package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", Server.SERVER_PORT)){
            System.out.println(socket.getInetAddress().getHostAddress());
            System.out.println(socket.getPort());
            System.out.println(socket.getLocalPort());
            System.out.println(socket.getRemoteSocketAddress());
            System.out.println(socket.getLocalSocketAddress());

        } catch (IOException e) {
            throw new RuntimeException("Error de E/S al abrir el socket en el cliente.", e);
        }

    }
}
