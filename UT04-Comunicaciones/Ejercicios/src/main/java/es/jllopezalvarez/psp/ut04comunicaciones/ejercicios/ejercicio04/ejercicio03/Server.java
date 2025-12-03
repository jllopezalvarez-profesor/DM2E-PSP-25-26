package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio04.ejercicio03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 8000;

    public static void main(String[] args) {
        System.out.println("Iniciando servidor y esperando conexión cliente...");
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
             Socket clientSocket = serverSocket.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new  OutputStreamWriter(clientSocket.getOutputStream()))) {
            System.out.printf("Conectado con el cliente. Puerto local: %s. Puerto remoto: %s.\n",
                    clientSocket.getLocalPort(), clientSocket.getPort());

            String operacion = reader.readLine();
            while (operacion!=null) {
                System.out.println("Operación recibida: " +  operacion);
                String numeroA = reader.readLine();
                System.out.println("Número A: " +  numeroA);
                String numeroB = reader.readLine();
                System.out.println("Número B: " +  numeroB);

                Thread.sleep(1000);

                String respuesta = procesarOperacion(operacion, numeroA, numeroB);
                System.out.println("Respuesta calculada: " + respuesta);
                writer.write(respuesta);
                writer.newLine();
                writer.flush();

                operacion = reader.readLine();
            }




        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException("El servidor ha sido interrumpido", e);
        }
        System.out.println("Finalizando servidor...");
    }

    private static String procesarOperacion(String operacion, String numeroA, String numeroB) {
        try {
            if (!((operacion.equals("+") || operacion.equals("-")))){
                return "Operación no válida";
            }
            double numA = Double.parseDouble(numeroA);
            double numB = Double.parseDouble(numeroB);

            double resultado = operacion.equals("+")?  numA + numB : numA - numB;

            return String.format("%f %s %f = %f", numA, operacion, numB, resultado);

        } catch (NumberFormatException e) {
            return "Alguno de los dos números no se podía convertir a doble.";
        }



    }

}
