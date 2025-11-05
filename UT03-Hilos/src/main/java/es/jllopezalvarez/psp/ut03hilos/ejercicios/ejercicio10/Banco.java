package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio10;

public class Banco {

    private static final long RETARDO_OPERACION = 200;

    private int saldo;

    public Banco(int saldo) {
        this.saldo = saldo;
    }

    public synchronized int getSaldo() {
        return saldo;
    }

    public synchronized boolean ingresar(int cantidad) throws InterruptedException {


        if (cantidad <= 0) {
            return false;
        }
        int nuevoSaldo = saldo + cantidad;

        Thread.sleep(RETARDO_OPERACION);

        saldo = nuevoSaldo;
        return true;
    }

    public synchronized boolean retirar(int cantidad) throws InterruptedException {


        if (cantidad <= 0) {
            return false;
        }
        if (cantidad > saldo) {
            return false;
        }
        int nuevoSaldo = saldo - cantidad;

        Thread.sleep(RETARDO_OPERACION);

        saldo = nuevoSaldo;
        return true;
    }
}
