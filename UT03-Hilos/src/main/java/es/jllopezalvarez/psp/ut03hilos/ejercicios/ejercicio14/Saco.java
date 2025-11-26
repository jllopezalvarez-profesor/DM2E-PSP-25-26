package es.jllopezalvarez.psp.ut03hilos.ejercicios.ejercicio14;

public class Saco {
    private final Material material;
    private final int peso;


    public Saco(Material material, int peso) {
        this.material = material;
        this.peso = peso;
    }

    public Material getMaterial() {
        return material;
    }

    public int getPeso() {
        return peso;
    }
}
