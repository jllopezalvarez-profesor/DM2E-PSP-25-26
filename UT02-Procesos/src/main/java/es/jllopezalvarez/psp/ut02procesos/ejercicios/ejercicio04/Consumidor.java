package es.jllopezalvarez.psp.ut02procesos.ejercicios.ejercicio04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class Consumidor {

    public static void main(String[] args) {

        boolean finished = false;

        while (!finished) {
            String lastLine = readLastLine(Constants.SHARED_FILE) ;
            if (lastLine.equals("FIN")) {
                System.out.println("Se ha encontrado 'FIN' en el fichero");
                finished = true;
            } else {
                System.out.println(lastLine);
            }
        }



    }

    private static String readLastLine(Path sharedFile) {
        String line = "";
        try(BufferedReader br = new BufferedReader(new FileReader(Constants.SHARED_FILE.toFile()))){
            String lastLine = br.readLine();
            while (lastLine != null) {
                line = lastLine;
                lastLine = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No existe el fichero o no tenemos acceso", e);
        } catch (IOException e) {
            throw new RuntimeException("Error de entrada salida al acceder al fichero", e);
        }
        return line;
    }
}
