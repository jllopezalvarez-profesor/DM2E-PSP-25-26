package es.jllopezalvarez.psp.ut04comunicaciones.ejemplos.ejemplo03udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Client {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final String SERVIDOR = "localhost";
        final int PUERTO = 5000;

        // Crear socket para enviar o recibir datos
        try (DatagramSocket socket = new DatagramSocket()) {

            // Número que se enviará
            int numero;

            // Preguntar el número y repetir hasta que sea cero
            System.out.println("Introduce el número del que quieres calcular la raíz cuadrada. Cero (0) para terminar.");
            while ((numero = scanner.nextInt()) != 0) {

                // Convertir int a bytes
                byte[] bufferSalida = ByteBuffer.allocate(Integer.BYTES).putInt(numero).array();

                // Obtener la dirección del servidor como objeto InetAddress
                InetAddress direccionServidor = InetAddress.getByName(SERVIDOR);

                // Montar paquete para enviar el número
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, PUERTO);

                // Enviar paquete
                socket.send(paqueteSalida);

                System.out.println("Número enviado: " + numero);

                // Buffer para recibir el double
                byte[] bufferEntrada = new byte[Double.BYTES];

                // Crear paquete para recibir datos
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);

                // Recibir los datos
                socket.receive(paqueteEntrada);

                // Convertir bytes a double
                double raiz = ByteBuffer.wrap(bufferEntrada).getDouble();

                System.out.println("Raíz recibida: " + raiz);

                // Volver a preguntar
                System.out.println("Introduce el número del que quieres calcular la raíz cuadrada. Cero (0) para terminar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
