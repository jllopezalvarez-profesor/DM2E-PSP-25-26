package es.jllopezalvarez.psp.ut04comunicaciones.ejercicios.ejercicio07;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;

public class Server {
    public static final String SERVER_NAME = "localhost";
    public static final int SERVER_PORT = 5000;
    public static final int BUFFER_SIZE = 1024; // 1KByte

    public static void main(String[] args) {

        // Crear el socket para "escuchar" en el puerto establecido
        try (DatagramSocket datagramSocket = new DatagramSocket(SERVER_PORT)) {
            // El servidor corre indefinidamente
            while (true) {
                // Como vamos a recibir un String, usar un inputBuffer de tamaño
                // mayor que lo que se puede recibir
                // Lo vamos a reutilizar para recepción y envío.
                byte[] inputBuffer = new byte[BUFFER_SIZE];

                // Crear datagrama para recibir paquetes
                DatagramPacket inputPacket = new DatagramPacket(inputBuffer, inputBuffer.length);

                // Recibir datos
                datagramSocket.receive(inputPacket);

                // Crear un ByteBuffer para leer cómodamente datos recibidos
                ByteBuffer inputByteBuffer = ByteBuffer.wrap(inputPacket.getData());

                // Leer el tamaño de la cadena que nos han enviado. Es un int.
                int messageStringSize = inputByteBuffer.getInt();

                // Crear inputBuffer para la lectura de los bytes de la cadena
                byte[] messageStringBuffer = new byte[messageStringSize];

                // Leer los bytes
                inputByteBuffer.get(messageStringBuffer);

                // Convertirlos a String
                String messageString = new String(messageStringBuffer, StandardCharsets.UTF_8);

                // Mostrarlos en consola
                System.out.printf("Recibida petición de cliente: %s\n", messageString);

                // Calcular el valor de respuesta
                messageString = switch (messageString){
                    case "H" -> LocalTime.now().toString();
                    case "F" -> LocalDate.now().toString();
                    default -> "ERROR";
                };

                // Convertir mensaje a Bytes
                 messageStringBuffer = messageString.getBytes(StandardCharsets.UTF_8);

                 // Tamaño suficiente para enviar el tamaño del array enviado + el array
                 ByteBuffer outputMessageBytBuffer = ByteBuffer.allocate(Integer.BYTES+messageStringBuffer.length);
                 outputMessageBytBuffer.putInt(messageStringBuffer.length);
                 outputMessageBytBuffer.put(messageStringBuffer);

                 // Montamos datagramas para enviar respuesta
                // Se configura el destino como el origen del paquete recibido previamente
                DatagramPacket outputPacket = new DatagramPacket(outputMessageBytBuffer.array(),
                        outputMessageBytBuffer.capacity(), inputPacket.getSocketAddress());

                // enviar
                datagramSocket.send(outputPacket);







            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
