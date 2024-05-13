package tp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorTxt {
	
	public static void main(String[] args) {
        String linea;
        int provOrigen, provDestino, pesoArista;

        try {
        	//Aca reemplazalo con tu txt de tu compu
            FileReader archivo = new FileReader("C:\\Users\\nator\\Downloads\\pruebaTxt.txt");
            BufferedReader lector = new BufferedReader(archivo);

            while ((linea = lector.readLine()) != null) {
                String[] numeros = linea.split(",");
                provOrigen = Integer.parseInt(numeros[0]);
                provDestino = Integer.parseInt(numeros[1]);
                pesoArista = Integer.parseInt(numeros[2]);
                
                //Algo como esto de aca abajo deberia de quedar creo
                //.agregarPesoArista(provOrigen, provDestino, pesoArista);

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
