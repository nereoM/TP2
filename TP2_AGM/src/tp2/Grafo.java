package tp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {

	private int[][] matrizDePesos;
	private int numVertices;
	private boolean[][] A;
	private Kruskal kruskal;

	public Grafo(int vertices) {
		this.matrizDePesos = new int[vertices][vertices];
		this.numVertices = matrizDePesos.length;
		this.A = new boolean[vertices][vertices];
		this.kruskal = new Kruskal();
		inicializarMatrizAdyPesos();
		inicializarMatrizAdy();
	}
	
	public int tamano()
	{
		return A.length;
	}
	
	private void inicializarMatrizAdy() {
		for (int i = 0; i < numVertices; i++) {
		    for (int j = 0; j < numVertices; j++) {
		        this.A[i][j] = false;
		    }
		}
	}
	
	public void inicializarMatrizAdyPesos() {
		for (int i = 0; i < numVertices; i++) {
		    for (int j = 0; j < numVertices; j++) {
		        this.matrizDePesos[i][j] = 0;
		    }
		}
	}
	
	public void agregarVecinos(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);
		
		A[i][j] = true;
		A[j][i] = true;
	}

	public void agregarPesoArista(int i, int j, int peso)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		matrizDePesos[i][j] = peso;
		matrizDePesos[j][i] = peso;
	}
	
	public int[][] devolverMatrizPesos() {
		int[][] pesos = matrizDePesos;
		return pesos;
	}
	
	public int pesoArista(int p1, int p2, int peso) {
		verificarDistintos(p1, p2);
		if (!existeArista(p1, p2)) {
			//throw new RuntimeException("La arista no existe.");
			return -1;
		}
		else if (peso == 0) {
			return -2;
		}
		else if (matrizDePesos[p1][p2] != 0) {
			return 0;
		}
		agregarPesoArista(p1, p2, peso);
		System.out.println("se agrego arista con peso " + peso);
		return 1;
	}
	
	private void verificarVertice(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		
		if( i >= A.length )
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}
	
	private void verificarDistintos(int i, int j)
	{
		if( i == j )
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}
	
	public boolean existeArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		return A[i][j];
	}
	
	public List<Arista> kruskal() {
		return kruskal.agm(numVertices, devolverMatrizPesos());	
	}

	/*
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
	*/
	
	/*
	public void aMatrizAdy(List<Arista> agm) { //la lista de aristas pasada tiene que ser la del agm
		for (Arista a : agm) {
			int vOrigen = a.getOrigen();
			int vDestino = a.getDestino();
			
			this.matrizAdy[vOrigen][vDestino] = true;
			this.matrizAdy[vDestino][vOrigen] = true;
		}
	}
	*/

}
