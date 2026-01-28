package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio07;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Client {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {

            System.out.print("¿Qué operación quieres hacer? F / H: ");
            String operacion = scanner.nextLine();

            // Convertir mensaje a byte[]
            byte[] messageStringBuffer = operacion.getBytes(StandardCharsets.UTF_8);

            // reservar buffer
            ByteBuffer outputMessageByteBuffer = ByteBuffer.allocate(Integer.BYTES + messageStringBuffer.length);

            // Escribir tamaño y contenido en ByteBuffer
            outputMessageByteBuffer.putInt(messageStringBuffer.length);
            outputMessageByteBuffer.put(messageStringBuffer);

            // Crear el datagrama
            DatagramPacket outputPacket = new DatagramPacket(outputMessageByteBuffer.array(),
                    outputMessageByteBuffer.capacity(),
                    InetAddress.getByName(Server.SERVER_NAME),
                    Server.SERVER_PORT);

            // Enviar el datagrama
            datagramSocket.send(outputPacket);

            // Bugffer para recibir
            byte[] inputMessageBuffer = new byte[Server.BUFFER_SIZE];

            // Crear datagrama para recibir paquetes
            DatagramPacket inputPacket = new DatagramPacket(inputMessageBuffer, inputMessageBuffer.length);

            // Recibir datos
            datagramSocket.receive(inputPacket);

            // Crear un ByteBuffer para leer cómodamente datos recibidos
            ByteBuffer inputByteBuffer = ByteBuffer.wrap(inputPacket.getData());

            // Leer el tamaño de la cadena que nos han enviado. Es un int.
            int messageStringSize = inputByteBuffer.getInt();

            // Crear inputBuffer para la lectura de los bytes de la cadena
            messageStringBuffer = new byte[messageStringSize];

            // Leer los bytes
            inputByteBuffer.get(messageStringBuffer);

            // Convertirlos a String
            String messageString = new String(messageStringBuffer, StandardCharsets.UTF_8);

            // Mostrarlos en consola
            System.out.printf("Recibida respuesta del servidor: %s\n", messageString);





        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
