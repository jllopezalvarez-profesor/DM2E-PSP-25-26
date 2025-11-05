package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio10;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int SALDO_INICIAL = 1000;
    private static final int NUM_CLIENTES = 20;

    public static void main(String[] args) throws InterruptedException {
        Banco banco = new Banco(SALDO_INICIAL);

        List<Cliente> clientes = new ArrayList<Cliente>();
        for (int i = 0; i < NUM_CLIENTES; i++) {
            clientes.add(new Cliente("Cliente " + i, banco));
        }

        for (Cliente cliente : clientes) {
            cliente.start();
        }

        for (Cliente cliente : clientes) {
            cliente.join();
        }



        int saldoClientes = clientes.stream() .mapToInt(Cliente::getCantidadTotal).sum();

        int saldoEsperado = SALDO_INICIAL + saldoClientes;

        System.out.printf("Se espereba %d y se ha obtenido %d\n",  saldoEsperado, banco.getSaldo());


    }
}
