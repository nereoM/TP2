package tp2;

import java.util.HashSet;
import java.util.Set;

public class Grafo
{
	// Representamos el grafo por su matriz de adyacencia
	private int[][] adyacenciaPesos;
	private boolean[][] A;
	
	// La cantidad de vertices esta predeterminada desde el constructor
	public Grafo(int vertices)
	{
		adyacenciaPesos = new int[vertices][vertices];
		this.A = new boolean[vertices][vertices];
		inicializarMatrizAdy();
		agregarVecinos();
	}
	
	private void inicializarMatrizAdy() {
		for (int i = 0; i < 24; i++) {
		    for (int j = 0; j < 24; j++) {
		        this.A[i][j] = false;
		    }
		}
	}
	
	private void agregarVecinos() {
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
	
	// Agregado de aristas
	public void agregarArista(int i, int j, int peso)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		adyacenciaPesos[i][j] = peso;
		adyacenciaPesos[j][i] = peso;
	}

	// Eliminacion de aristas
	public void eliminarArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		adyacenciaPesos[i][j] = 0;
		adyacenciaPesos[j][i] = 0;
	}

	// Informa si existe la arista especificada
	public boolean existeArista(int i, int j)
	{
		//verificarVertice(i);
		//verificarVertice(j);
		//verificarDistintos(i, j);
		
		return A[i][j];
	}

	// Cantidad de vertices
	public int tamano()
	{
		return A.length;
	}
	
	// Vecinos de un vertice
	public Set<Integer> vecinos(int i)
	{
		verificarVertice(i);
		
		Set<Integer> ret = new HashSet<Integer>();
		for(int j = 0; j < this.tamano(); ++j) if( i != j )
		{
			if( this.existeArista(i,j) )
				ret.add(j);
		}
		
		return ret;		
	}
	
	public int[][] devolverMatrizAdy() {
		int [][] matrizAdy = this.adyacenciaPesos;
		
		return matrizAdy;
	}
	
	public int devolverContCoord(int x, int y) {
		return adyacenciaPesos[x][y];
	}
	
	// Verifica que sea un vertice valido
	private void verificarVertice(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		
		if( i >= A.length )
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j)
	{
		if( i == j )
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}
}

