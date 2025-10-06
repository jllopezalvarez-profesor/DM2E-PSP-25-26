package es.jllopezalvarez.psp.ut02procesos.ejercicios.ejercicio04;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;

public class Consumidor {

    public static void main(String[] args) {

        boolean finished = false;

        String lastLine;
        while (!finished) {
            try (FileOutputStream lockFos = new FileOutputStream(Constants.LOCK_FILE.toFile());
                 FileChannel lockChannel = lockFos.getChannel();
                 FileLock lock = lockChannel.lock()
            ) {

//            String lastLine = readLastLine(Constants.SHARED_FILE) ;
                lastLine = readLastLineWithNIO(Constants.SHARED_FILE);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Error de acceso a fichero de bloqueo", e);
            } catch (IOException e) {
                throw new RuntimeException("Error de E/S al bloquear", e);
            }
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
        try (BufferedReader br = new BufferedReader(new FileReader(Constants.SHARED_FILE.toFile()))) {
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

    private static String readLastLineWithNIO(Path sharedFile) {
        try {
            return Files.readAllLines(sharedFile).getLast();
        } catch (NoSuchElementException e) {
            return "";
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No existe el fichero o no tenemos acceso", e);
        } catch (IOException e) {
            throw new RuntimeException("Error de E/S al acceder al fichero", e);
        }


    }

}
