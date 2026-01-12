package es.jllopezalvarez.psp.ut04comunicaciones.ejemplos.ejemplo03udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class Server {

    public static void main(String[] args) {
        final int PUERTO = 5000;

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP escuchando en el puerto " + PUERTO);

            while (true) {
                // Buffer para recibir el entero (4 bytes)
                byte[] bufferEntrada = new byte[Integer.BYTES];

                // Crear paquete para recibir datos
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);

                // Recibir paquete del cliente
                socket.receive(paqueteEntrada);

                // Convertir bytes a int
                int numero = ByteBuffer.wrap(bufferEntrada).getInt();

                System.out.println("Número recibido: " + numero);

                // Calcular raíz cuadrada
                double raiz = Math.sqrt(numero);

                System.out.println("Raíz calculada: " + raiz);

                // Convertir double a bytes (8 bytes)
                byte[] bufferSalida = ByteBuffer.allocate(Double.BYTES).putDouble(raiz).array();

                // Obtener dirección y puerto de respuesta del paquete del cliente
                InetAddress direccionCliente = paqueteEntrada.getAddress();
                int puertoCliente = paqueteEntrada.getPort();

                // Preparar paquete de respuesta
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionCliente, puertoCliente);

                // Enviar el paquete
                socket.send(paqueteSalida);

                System.out.println("Respuesta enviada al cliente");
            }

        } catch (Exception e) {
            System.err.println("Error en el servidor");
            e.printStackTrace();
        }
    }
}
