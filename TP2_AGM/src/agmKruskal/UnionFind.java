package agmKruskal;

public class UnionFind {
	
private int[] componentesConexas;
	
	public UnionFind(int vertices) {
		this.componentesConexas = new int[vertices];
		for (int i = 0; i < this.componentesConexas.length; i++) {
			this.componentesConexas[i] = i;
		}
	}
	
	public int raiz(int i) {
		while (this.componentesConexas[i] != i) {
			i = this.componentesConexas[i];
		}
		return i;
	}
	
	public boolean find(int i, int j) {
		return raiz(i) == raiz(j);
	}
	
	public void union(int i, int j) {
		int ri = raiz(i);
		int rj = raiz(j);
		this.componentesConexas[ri] = rj;
	}

}
