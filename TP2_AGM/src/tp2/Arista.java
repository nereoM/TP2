package tp2;

public class Arista implements Comparable<Arista> {
	
	private int origen;
	private int destino;
	private int peso;
	 
	public Arista(int origen, int destino, int peso) {
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
	}

	public int getOrigen() {
		return origen;
	}

	public int getDestino() {
		return destino;
	}
	 
	public int getPeso() {
		return peso;
	}

	@Override
	public int compareTo(Arista otraArista) {
		return Integer.compare(this.peso, otraArista.getPeso());
	}

}
