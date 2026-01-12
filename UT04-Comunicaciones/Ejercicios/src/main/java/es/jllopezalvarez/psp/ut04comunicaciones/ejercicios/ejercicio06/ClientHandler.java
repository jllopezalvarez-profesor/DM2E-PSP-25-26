package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio06;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.function.Function;

public class ClientHandler extends Thread {
    private static final double MI2KM_FACTOR = 1.609;
    private static final double KG2LB_FACTOR = 2.205;
    private static final double C2F_FACTOR = (double) 9 / 5;
    private static final double F2C_FACTOR = (double) 5 / 9;
    private static final double F2C2F_DIFF = 32;


    private final Socket clientSocket;

    private final Map<String, Function<Double, Double>> processFunctions = Map.of(
            "KM2MI", (value) -> value / MI2KM_FACTOR, // convierte kilómetros a millas.

            "MI2KM", (value) -> {
                return value * MI2KM_FACTOR; // convierte millas a kilómetros.
            },
            "C2F", (value) -> {
                return (value * C2F_FACTOR) + F2C2F_DIFF;
            }, // convierte grados Celsius a Fahrenheit.
            "F2C", (value) -> {
                return (value - F2C2F_DIFF) * F2C_FACTOR;
            }, //convierte grados Fahrenheit a Celsius.
            "KG2LB", (value) -> {
                return value * KG2LB_FACTOR;
            }, //convierte kilogramos a libras.
            "LB2KG", (value) -> {
                return value / KG2LB_FACTOR;
            } //convierte libras a kilogramos.

    );

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                String operacionCliente = dis.readUTF();
                double valor = dis.readDouble();

                double returnValue = Double.MIN_VALUE;

                if (processFunctions.containsKey(operacionCliente)) {
                    returnValue = processFunctions.get(operacionCliente).apply(valor);
                }

                dos.writeDouble(returnValue);
                dos.flush();
            }
        } catch (EOFException e) {
            // No se hace nada porque sirve para "romper" el bucle
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
