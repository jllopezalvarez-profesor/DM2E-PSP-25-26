package es.jllopezalvarez.psp.ut03hilos.ejemplos.ejemplo06semaphore;

class Usuario extends Thread {
    private final int numDocumentos;
    private final Reprografia reprografia;

    public Usuario(String nombre, int numDocumentos, Reprografia centro) {
        super(nombre);
        this.numDocumentos = numDocumentos;
        this.reprografia = centro;
    }

    @Override
    public void run() {
        try {
            for (int numDoc = 1; numDoc <= numDocumentos; numDoc++) {
                reprografia.imprimir(this.getName(), numDoc);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}