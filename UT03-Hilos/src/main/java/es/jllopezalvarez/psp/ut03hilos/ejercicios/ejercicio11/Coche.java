package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio11;

public class Coche implements Runnable {

    private final String matricula;
    private final Puente puente;

    public Coche(String matricula, Puente puente) {
        this.matricula = matricula;
        this.puente = puente;
    }

    @Override
    public void run() {

        System.out.printf("El coche con matrícula %s arranca.\n",  matricula);

        try {
            puente.cruzar(this.matricula);
        } catch (InterruptedException e) {
            System.out.printf("El coche con matricula %s ha sido interrumpido.\n", matricula);
        }


        System.out.printf("El coche con matrícula %s para.\n",  matricula);

    }
}
