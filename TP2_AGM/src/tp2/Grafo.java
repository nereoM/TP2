package tp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {

	private int[][] matrizDePesos;
	private int numVertices, cantAristasConPeso;
	private boolean[][] A;
	private Kruskal kruskal;

	public Grafo(int vertices) {
		if (vertices == 0) {
			throw new IllegalArgumentException("No se puede crear un grafo vacio.");
		}
		this.matrizDePesos = new int[vertices][vertices];
		this.numVertices = matrizDePesos.length;
		this.A = new boolean[vertices][vertices];
		this.kruskal = new Kruskal();
		this.cantAristasConPeso = 0;
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

	public void agregarPesoAristaAux(int i, int j, int peso)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		matrizDePesos[i][j] = peso;
		matrizDePesos[j][i] = peso;
	}
	
	public boolean[][] devolverMatrizAdy() {
        boolean[][] ady = A;
        return ady;
    }
	
	public int[][] devolverMatrizPesos() {
		int[][] pesos = matrizDePesos;
		return pesos;
	}
	
	public int agregarPesoArista(int p1, int p2, int peso) {
		try {
			verificarDistintos(p1, p2);
			if (!existeArista(p1, p2)) {
				return -1;
			}
			else if (peso == 0) {
				return -2;
			}
			else if (peso < 0) {
				peso *= (-1);
			}
			agregarPesoAristaAux(p1, p2, peso);
			System.out.println("se agrego arista con peso " + peso);
			cantAristasConPeso++;
			return 1;
		}
		catch (IllegalArgumentException e) {
			return 2;
		}
	}
	
	public int getCantAristasConPeso() {
		return cantAristasConPeso;
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
	
	public List<Arista> quitarK_1Aristas(List<Arista> agm, int cantRegiones) {
		if (agm.size() == 0 || cantRegiones > agm.size() || cantRegiones <= 0) {
			throw new IllegalArgumentException();
		}
		int k = cantRegiones - 1;
		for (int i = 0; i < k; i++) {
			int max = agm.get(0).getPeso();
			Arista aMax = agm.get(0);
			for (Arista a:agm) {
				if (a.getPeso() > max) {
					max = a.getPeso();
					aMax = a;
				}
			}
			agm.remove(aMax);
		}
		return agm;
	}
	
	public void reiniciarContadorAristasConPeso() {
		this.cantAristasConPeso = 0;
	}

}
