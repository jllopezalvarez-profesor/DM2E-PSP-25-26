package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 4000;

    public static void main(String[] args) {
        System.out.println("Iniciando servidor...");
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
             Socket clientSocket = serverSocket.accept()) {

            System.out.printf("Cliente conectado. Puerto del cliente: %n\n", clientSocket.getPort());

            try (BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter clientWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                String linea;
                while ((linea = clientReader.readLine()) != null) {
                    System.out.printf("Se ha recibido %s del cliente.\n", linea);
                    clientWriter.write(procesarDato(linea));
                    clientWriter.newLine();
                    clientWriter.flush();
                }



            }


        } catch (IOException e) {
            System.out.println("Error de E/S al conectar o comunicar con el cliente");
            System.out.println(e.getMessage());
        }
        System.out.println("Servidor finalizado.");
    }

    private static String procesarDato(String linea) {
        try{
            int numero = Integer.parseInt(linea);
            return String.valueOf(numero*2);
        } catch (NumberFormatException e) {
            return "El valore recibido no es un número entero.";
        }

    }


}
