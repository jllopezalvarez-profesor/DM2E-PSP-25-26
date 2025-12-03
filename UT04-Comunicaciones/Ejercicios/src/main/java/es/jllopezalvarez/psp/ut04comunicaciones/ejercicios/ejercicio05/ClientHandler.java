package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class ClientHandler extends Thread {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)){

            String peticionCliente = reader.readLine();
            while(peticionCliente != null && isInterrupted()){
                String respuesta = switch (peticionCliente){
                    case "F" -> LocalDate.now().toString();
                    case "H" -> LocalTime.now().toString();
                    default -> "Petición no válida";
                };
                writer.println(respuesta);
                peticionCliente = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        super.run();
    }
}
