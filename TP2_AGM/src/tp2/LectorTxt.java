package tp2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LectorTxt {
	
	public void leerArchivo(Grafo grafo) {
        String linea;
        int provOrigen, provDestino, pesoArista;
        String nombreArchivo = "src/archivos/ejemploPrueba.txt";
        //File archivo = new File(nombreArchivo);

        try {
        	Path path = Paths.get("src/archivos/ejemploprueba");
        	linea = path.toString();        	//Aca reemplazalo con tu txt de tu compu
            FileReader lectorArchivo = new FileReader(linea);
            BufferedReader lector = new BufferedReader(lectorArchivo);

            while ((linea = lector.readLine()) != null) {
                String[] numeros = linea.split(",");
                provOrigen = Integer.parseInt(numeros[0]);
                provDestino = Integer.parseInt(numeros[1]);
                pesoArista = Integer.parseInt(numeros[2]);
                
                //Algo como esto de aca abajo deberia de quedar creo
                grafo.agregarPesoArista(provOrigen, provDestino, pesoArista);

                System.out.println("Número 1: " + provOrigen);
                System.out.println("Número 2: " + provDestino);
                System.out.println("Número 3: " + pesoArista);
                System.out.println("------------------------");
            }

            lector.close();
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
	}
}
