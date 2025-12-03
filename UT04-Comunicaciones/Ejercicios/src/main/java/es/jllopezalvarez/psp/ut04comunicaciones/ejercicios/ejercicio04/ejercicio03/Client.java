package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio04.ejercicio03;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Iniciando cliente y conectando a servidor...");

        try (Socket socket = new Socket("localhost", Server.SERVER_PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            System.out.printf("Conectado con el cliente. Puerto local: %s. Puerto remoto: %s.\n",
                    socket.getLocalPort(), socket.getPort());
            System.out.println("Abriendo streams");

            System.out.print("Dime la operación (+,-), SALIR para terminar: ");
            String operacion = scanner.nextLine();
            while (!operacion.equals("SALIR")) {
                System.out.print("Dime el primer número (puede tener decimales):");
                String numeroA = scanner.nextLine();
                System.out.print("Dime el segundo número (puede tener decimales):");
                String numeroB = scanner.nextLine();

                writer.write(operacion);
                writer.newLine();
                writer.write(numeroA);
                writer.newLine();
                writer.write(numeroB);
                writer.newLine();

                writer.flush();


                String respuesta = reader.readLine();


                System.out.printf("El servidor ha respondido: %s\n", respuesta);

                System.out.print("Dime la operación (+,-), SALIR para terminar: ");
                operacion = scanner.nextLine();
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
