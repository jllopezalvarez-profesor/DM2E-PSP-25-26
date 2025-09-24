package es.jllopezalvarez.psp.ut02procesos.ejemplos.ejemplo08sincronizacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainProcess {

    private static final Random RANDOM = new Random();

    // Comando para procesos hijos. En forma de array para que sea más fácil crear el ProcessBuilder.
    private static final String[] CHILDREN_COMMAND = {"java", "-cp", "./target/classes", "es.jllopezalvarez.psp.ut02procesos.ejemplos.ejemplo08sincronizacion.ChildProcess"};

    public static void main(String[] args) {

        // Crear un processBuilder para poder lanzar varias veces el mismo proceso (varios hijos)
        ProcessBuilder childrenProcessBuilder = new ProcessBuilder(CHILDREN_COMMAND);

        // Lista de procesos para poder lanzar un número determinado de procesos
        List<Process> processes = new ArrayList<>();

        try {
            // Calcular un número aleatorio de hijos que se lanzarán
            int numberOfProcesses = RANDOM.nextInt(10, 20+1); // Entre 10 y 20 a.i.
            // Lanzar los hijos, y guardar la referencia a los procesos creados en la lista
            for (int i = 0; i < numberOfProcesses; i++) {
                processes.add(childrenProcessBuilder.start());
            }

            // Esperar a todos los procesos. Se hace con espera activa para poder monitorizar cuantos está
            // todavía activos. Si se usara .waitFor en el bucle sería algo más eficiente.
            // Inicialmente, se asume que todos los procesos siguen en marcha.
            long childrenAlive = processes.size();
            System.out.printf("Se han lanzado %d procesos hijos.\n",  childrenAlive);
            // Mientras que haya hijos en marcha...
            while (childrenAlive > 0) {
                // Informar
                System.out.printf("Procesos aún en ejecución: %d\n", childrenAlive);
                // Calcular cuantos siguen en marcha (expresión funcional / stream API)
                childrenAlive = processes.stream().filter(Process::isAlive).count();
                // Dormir el proceso principal
                Thread.sleep(1000);
            }
            System.out.println("Todos los procesos han finalizado.");
        } catch (IOException e) {
            throw new RuntimeException("Error de E/S al ejecutar procesos hijos.", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Proceso principal interrumpido mientras esperaba a un proceso hijo", e);
        }
    }
}
