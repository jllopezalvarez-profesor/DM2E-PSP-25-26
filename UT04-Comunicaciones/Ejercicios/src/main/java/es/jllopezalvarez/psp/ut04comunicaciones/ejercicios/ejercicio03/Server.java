package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 8000;

    public static void main(String[] args) {
        System.out.println("Iniciando servidor y esperando conexión cliente...");
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
                 Socket clientSocket = serverSocket.accept();
                 DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                 DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream())) {
                System.out.printf("Conectado con el cliente. Puerto local: %s. Puerto remoto: %s.\n",
                        clientSocket.getLocalPort(), clientSocket.getPort());


                try {
                    while (true) {
                        String operacion = inputStream.readUTF();
                        System.out.println("Operación recibida: " + operacion);
                        double numeroA = inputStream.readDouble();
                        System.out.println("Número A: " + numeroA);
                        double numeroB = inputStream.readDouble();
                        System.out.println("Número B: " + numeroB);

                        Thread.sleep(1000);

                        String respuesta = procesarOperacion(operacion, numeroA, numeroB);
                        System.out.println("Respuesta calculada: " + respuesta);
                        outputStream.writeUTF(respuesta);
                        outputStream.flush();
                    }
                } catch (EOFException e) {
                    // Se deja vacío porque permite salir del bucle de lectura por fin de fichero
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException("El servidor ha sido interrumpido", e);
            }

        }
        // System.out.println("Finalizando servidor...");

    }

    private static String procesarOperacion(String operacion, double numeroA, double numeroB) {
        if (!((operacion.equals("+") || operacion.equals("-")))) {
            return "Operación no válida";
        }
        double resultado = operacion.equals("+") ? numeroA + numeroB : numeroA - numeroB;
        return String.format("%f %s %f = %f", numeroA, operacion, numeroB, resultado);
    }

}
