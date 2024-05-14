package tp2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LectorTxt {
	
	public int leerArchivo(Grafo grafo) {
        String linea;
        int provOrigen, provDestino, pesoArista;
        int cantNoAgregadas = 0;
        int agregar;

        try {
        	Path path = Paths.get("src/archivos/ejemploprueba");
        	linea = path.toString();
            FileReader lectorArchivo = new FileReader(linea);
            BufferedReader lector = new BufferedReader(lectorArchivo);

            while ((linea = lector.readLine()) != null) {
                String[] numeros = linea.split(",");
                provOrigen = Integer.parseInt(numeros[0]);
                provDestino = Integer.parseInt(numeros[1]);
                pesoArista = Integer.parseInt(numeros[2]);
                
                agregar = grafo.agregarPesoArista(provOrigen, provDestino, pesoArista);
                
                if (agregar == -1 || agregar == -2 || agregar == 2) {
                	cantNoAgregadas++;
                }

                System.out.println("Número 1: " + provOrigen);
                System.out.println("Número 2: " + provDestino);
                System.out.println("Similaridad: " + pesoArista);
                System.out.println("------------------------");
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return cantNoAgregadas;
	}
}
