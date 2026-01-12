package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio06;

import com.sun.source.doctree.EscapeTree;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", Server.SERVER_PORT);
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Operaciones disponibles: ");
            System.out.println("""
                                ▪ "KM2MI" → Convierte kilómetros a millas.
                                ▪ "MI2KM" → Convierte millas a kilómetros.
                                ▪ "C2F" → Convierte grados Celsius a Fahrenheit.
                                ▪ "F2C" → Convierte grados Fahrenheit a Celsius.
                                ▪ "KG2LB" → Convierte kilogramos a libras.
                                ▪ "LB2KG" → Convierte libras a kilogramos.
                                ▪ "FIN" → Terminar.
                    """);
            System.out.print("Introduce operación: ");
            String peticion = sc.nextLine();
            while (!peticion.equals("FIN")) {
                dos.writeUTF(peticion);
                System.out.print("Introduce el valor a convertir: ");
                double valor = Double.parseDouble(sc.nextLine());
                dos.writeDouble(valor);
                dos.flush();

                double respuesta = dis.readDouble();
                if (respuesta == Double.MIN_VALUE) {
                    System.out.println("El servidor ha respondido con código de error");
                } else {
                    System.out.printf("El servidor ha dicho: %.2f\n", respuesta);

                    System.out.println("Operaciones disponibles: ");
                    System.out.println("""
                                        ▪ "KM2MI" → Convierte kilómetros a millas.
                                        ▪ "MI2KM" → Convierte millas a kilómetros.
                                        ▪ "C2F" → Convierte grados Celsius a Fahrenheit.
                                        ▪ "F2C" → Convierte grados Fahrenheit a Celsius.
                                        ▪ "KG2LB" → Convierte kilogramos a libras.
                                        ▪ "LB2KG" → Convierte libras a kilogramos.
                                        ▪ "FIN" → Terminar.
                            """);
                    System.out.print("Introduce operación: ");
                    peticion = sc.nextLine();
                }
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
