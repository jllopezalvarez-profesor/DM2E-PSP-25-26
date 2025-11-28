package es.jllopezalvarez.psp.ejemplossockets.ejemplo01primeraconexion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try (

                // Socket socket = new Socket()
                Socket socket = new Socket("localhost", 8000)

        ) {


            System.out.println("Conectado con el servidor");

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
