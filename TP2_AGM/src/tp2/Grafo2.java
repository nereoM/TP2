package tp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo2 {

	private int[][] matrizDePesos;
	private int numVertices;
	private boolean[][] A;

	public Grafo2(int vertices) {
		this.matrizDePesos = new int[vertices][vertices];
		this.numVertices = matrizDePesos.length;
		this.A = new boolean[vertices][vertices];
		inicializarMatrizAdyPesos();
		inicializarMatrizAdy();
		vecinos();
	}
	
	public int tamano()
	{
		return A.length;
	}
	
	private void inicializarMatrizAdy() {
		for (int i = 0; i < 24; i++) {
		    for (int j = 0; j < 24; j++) {
		        this.A[i][j] = false;
		    }
		}
	}
	
	public void inicializarMatrizAdyPesos() {
		for (int i = 0; i < 24; i++) {
		    for (int j = 0; j < 24; j++) {
		        this.matrizDePesos[i][j] = 0;
		    }
		}
	}
	
	private void vecinos() {
		A[0][1] = true;
		A[1][0] = true;
		A[1][2] = true;
		A[1][3] = true;
		A[1][4] = true;
		A[1][5] = true;
		A[1][6] = true;
		A[2][1] = true;
		A[2][6] = true;
		A[3][1] = true;
		A[3][4] = true;
		A[3][5] = true;
		A[3][10] = true;
		A[3][11] = true;
		A[4][1] = true;
		A[4][3] = true;
		A[4][5] = true;
		A[5][1] = true;
		A[5][4] = true;
		A[5][3] = true;
		A[5][6] = true;
		A[5][9] = true;
		A[5][11] = true;
		A[6][1] = true;
		A[6][2] = true;
		A[6][5] = true;
		A[6][7] = true;
		A[6][9] = true;
		A[7][6] = true;
		A[7][9] = true;
		A[7][12] = true;
		A[7][8] = true;
		A[8][7] = true;
		A[9][5] = true;
		A[9][6] = true;
		A[9][7] = true;
		A[9][11] = true;
		A[9][12] = true;
		A[9][15] = true;
		A[10][3] = true;
		A[10][11] = true;
		A[10][13] = true;
		A[10][14] = true;
		A[11][3] = true;
		A[11][5] = true;
		A[11][9] = true;
		A[11][10] = true;
		A[11][14] = true;
		A[11][15] = true;
		A[11][18] = true;
		A[12][7] = true;
		A[12][9] = true;
		A[12][15] = true;
		A[13][10] = true;
		A[13][14] = true;
		A[13][17] = true;
		A[14][10] = true;
		A[14][11] = true;
		A[14][13] = true;
		A[14][17] = true;
		A[14][18] = true;
		A[15][9] = true;
		A[15][11] = true;
		A[15][12] = true;
		A[15][18] = true;
		A[15][20] = true;
		A[15][16] = true;
		A[16][15] = true;
		A[17][13] = true;
		A[17][14] = true;
		A[17][18] = true;
		A[17][19] = true;
		A[18][11] = true;
		A[18][14] = true;
		A[18][15] = true;
		A[18][17] = true;
		A[18][19] = true;
		A[18][20] = true;
		A[19][17] = true;
		A[19][18] = true;
		A[19][20] = true;
		A[20][15] = true;
		A[20][18] = true;
		A[20][19] = true;
		A[20][21] = true;
		A[21][20] = true;
		A[21][22] = true;
		A[22][21] = true;
		A[22][23] = true;
		A[23][22] = true;
	}

	public void agregarPesoArista(int i, int j, int peso)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		matrizDePesos[i][j] = peso;
		matrizDePesos[j][i] = peso;
	}
	
	public int pesoArista(int p1, int p2, int peso) {
		if (!existeArista(p1, p2)) {
			//throw new RuntimeException("La arista no existe.");
			return -1;
		}
		else if (matrizDePesos[p1][p2] != 0) {
			return 0;
		}
		agregarPesoArista(p1, p2, peso);
		/*
		if (!verticesAgregados.contains(p1)) {
			verticesAgregados.add(p1);
		}
		if (!verticesAgregados.contains(p2)) {
			verticesAgregados.add(p2);
		}
		*/
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
		//verificarVertice(i);
		//verificarVertice(j);
		//verificarDistintos(i, j);
		
		return A[i][j];
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
