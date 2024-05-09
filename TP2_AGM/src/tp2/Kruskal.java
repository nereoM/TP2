package tp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
	
	public Kruskal() {

	}

	
	public List<Arista> obtenerTodasLasAristas(int numVertices, int[][] matrizDePesos) {
		List<Arista> aristas = new ArrayList<>();
		for (int i = 0; i < numVertices; i++) {
			for (int j = i + 1; j < numVertices; j++) {
				if (matrizDePesos[i][j] != 0) {
					Arista arista = new Arista(i, j, matrizDePesos[i][j]);
					aristas.add(arista);
				}
			}
		}
		return aristas;
	}

	public List<Arista> kruskal(int numVertices, int[][] matrizDePesos) {
		List<Arista> aristas = obtenerTodasLasAristas(numVertices, matrizDePesos);
		Collections.sort(aristas); //Ordena de las aristas de menor a mayor segun su peso

		UnionFind unionFind = new UnionFind(numVertices); //Se crean las componentes conexas iniciales

		List<Arista> agm = new ArrayList<>();
		for (Arista arista : aristas) {
			int origen = arista.getOrigen();
			int destino = arista.getDestino();
			if (!unionFind.find(origen, destino)) { //Estan en la misma componente conexa?
				agm.add(arista);
				unionFind.union(origen, destino); //Apuntar a la misma raiz
			}
		}
		return agm; //Lista de aristas del arbol generador minimo
	}
}
