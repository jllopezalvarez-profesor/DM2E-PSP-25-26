package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio10;

import java.util.Random;

public class Cliente extends Thread {

    private static final int NUM_OPERACIONES = 10;
    private static final long MIN_PAUSA = 1;
    private static final long MAX_PAUSA = 2;
    private static final int MIN_OPERACION = 100;
    private static final int MAX_OPERACION = 200;

    private final Random rnd = new Random();
    private final Banco banco;
    private int cantidadTotal = 0;

    public Cliente(String name, Banco banco) {
        super(name);
        this.banco = banco;
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < NUM_OPERACIONES; i++) {
                if (this.isInterrupted()) {
                    throw new InterruptedException("Interrumpido 'normalmente'");
                }

                int cantidadOperacion = rnd.nextInt(MIN_OPERACION, MAX_OPERACION);
                boolean esRetirada = rnd.nextBoolean();
                System.out.printf("%s va a realizar una operación de %d (%s).\n", this.getName(), cantidadOperacion, esRetirada ? "retirada" : "ingreso");
                System.out.printf("El saldo antes de la operación es de %d\n", banco.getSaldo());
                if (esRetirada) {
                    if (banco.retirar(cantidadOperacion)) {
                        cantidadTotal -= cantidadOperacion;
                    } else {
                        System.out.printf("A %s le ha fallado una retirada de %d.\n", this.getName(), cantidadOperacion);
                    }
                } else {
                    if (banco.ingresar(cantidadOperacion)) {
                        cantidadTotal += cantidadOperacion;
                    } else {
                        System.out.printf("A %s le ha fallado un ingreso de %d.\n", this.getName(), cantidadOperacion);
                    }

                }
                System.out.printf("El saldo después de la operación es de %d\n", banco.getSaldo());

                Thread.sleep(rnd.nextLong(MIN_PAUSA, MAX_PAUSA));


            }
        } catch (InterruptedException e) {
            System.out.println("Interrumpido\n");
        }


    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }
}
