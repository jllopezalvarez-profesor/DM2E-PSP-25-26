package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio02;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {


    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Iniciando cliente...");

        try(Socket socket = new Socket("localhost", Server.SERVER_PORT)){
            System.out.println("Conectado con el servidor...");

            try (BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter serverWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                System.out.println("Streams con el servidor abiertos.");

                System.out.print("Introduce un número, 0 para terminar: ");
                String numero = scanner.nextLine().trim();
                while(!numero.equals("0")){
                    System.out.printf("Leido %s del usuario.\n", numero);
                    serverWriter.write(numero);
                    serverWriter.newLine();
                    serverWriter.flush();

                    System.out.println("Enviado al servidor, espero respuesta...");
                    String serverResponse = serverReader.readLine();
                    System.out.printf("El servidor ha respondido %s\n", serverResponse);

                    System.out.print("Introduce un número, 0 para terminar: ");
                    numero = scanner.nextLine().trim();
                }
            }

        } catch (UnknownHostException e) {
            System.out.println("Host servidor no encontrado");
        } catch (IOException e) {
            System.out.println("Error de E/S al conectar / comunicar con el servidor");
            System.out.println(e.getMessage());
        }

        System.out.println("Client finalizando");
    }

}
