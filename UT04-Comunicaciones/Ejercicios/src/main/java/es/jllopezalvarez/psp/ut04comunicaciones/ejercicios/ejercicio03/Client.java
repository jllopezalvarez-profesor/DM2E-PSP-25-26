package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio03;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Iniciando cliente y conectando a servidor...");

        try (Socket socket = new Socket("localhost", Server.SERVER_PORT);
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
            System.out.printf("Conectado con el cliente. Puerto local: %s. Puerto remoto: %s.\n",
                    socket.getLocalPort(), socket.getPort());
            System.out.println("Abriendo streams");

            System.out.print("Dime la operación (+,-), SALIR para terminar: ");
            String operacion = scanner.nextLine();
            while (!operacion.equals("SALIR")) {
                System.out.print("Dime el primer número (puede tener decimales):");
                double numeroA = Double.parseDouble(scanner.nextLine());
                System.out.print("Dime el segundo número (puede tener decimales):");
                double numeroB = Double.parseDouble( scanner.nextLine());

                outputStream.writeUTF(operacion);
                outputStream.writeDouble(numeroA);
                outputStream.writeDouble(numeroB);
                outputStream.flush();

                String respuesta = inputStream.readUTF();

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
