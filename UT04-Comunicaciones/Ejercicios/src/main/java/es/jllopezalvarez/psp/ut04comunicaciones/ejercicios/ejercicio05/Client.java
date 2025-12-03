package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", Server.SERVER_PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)){
            System.out.print("Dime qué quieres saber, fecha (F) u hora (H): ");
            String peticion =  sc.nextLine();

            writer.println(peticion);

            String respuesta =  reader.readLine();

            System.out.printf("El servidor ha dicho: %s\n", respuesta);





        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
