package agmKruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {

	private int[][] matrizDePesos;
	private int numVertices;
	private boolean[][] matrizAdy;

	public Grafo(int vertices) {
		this.matrizDePesos = new int[vertices][vertices];
		this.numVertices = matrizDePesos.length;
		this.matrizAdy = new boolean[vertices][vertices];
	}

	public void agregarAristaConPeso(int i, int j, int peso) {
		this.matrizDePesos[i][j] = peso;
		this.matrizDePesos[j][i] = peso;
	}

	public List<Arista> obtenerTodasLasAristas() {
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

	public List<Arista> kruskal() {
		List<Arista> aristas = obtenerTodasLasAristas();
		Collections.sort(aristas); //Ordena de las aristas de menor a mayor segun su peso

		UnionFind unionFind = new UnionFind(this.numVertices); //Se crean las componentes conexas iniciales

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
	
	public void aMatrizAdy(List<Arista> agm) { //la lista de aristas pasada tiene que ser la del agm
		for (Arista a : agm) {
			int vOrigen = a.getOrigen();
			int vDestino = a.getDestino();
			
			this.matrizAdy[vOrigen][vDestino] = true;
			this.matrizAdy[vDestino][vOrigen] = true;
		}
	}

	public static void main(String[] args) {

		Grafo miGrafo = new Grafo(9);
		miGrafo.agregarAristaConPeso(0, 1, 4);
		miGrafo.agregarAristaConPeso(0, 7, 8);
		miGrafo.agregarAristaConPeso(1, 2, 8);
		miGrafo.agregarAristaConPeso(1, 7, 12);
		miGrafo.agregarAristaConPeso(2, 8, 3);
		miGrafo.agregarAristaConPeso(2, 3, 6);
		miGrafo.agregarAristaConPeso(2, 5, 4);
		miGrafo.agregarAristaConPeso(3, 5, 13);
		miGrafo.agregarAristaConPeso(3, 4, 9);
		miGrafo.agregarAristaConPeso(4, 5, 10);
		miGrafo.agregarAristaConPeso(5, 6, 3);
		miGrafo.agregarAristaConPeso(6, 8, 5);
		miGrafo.agregarAristaConPeso(6, 7, 1);
		miGrafo.agregarAristaConPeso(7, 8, 6);

		List<Arista> agm = miGrafo.kruskal();

		for (Arista a : agm) {
			System.out.println(a.getOrigen() + " ---> " + a.getDestino() + " de peso: " + a.getPeso());
		}
		
		miGrafo.aMatrizAdy(agm);
		
		for (int i = 0; i < miGrafo.matrizAdy.length; i++) {
			for (int j = 0; j < miGrafo.matrizAdy[i].length; j++) {
				System.out.print(miGrafo.matrizAdy[i][j] + " ");
			}
			System.out.println();
		}
	}

}
