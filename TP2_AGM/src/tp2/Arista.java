package tp2;

public class Arista {
    int origen, destino, peso;

    public Arista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
    
    public int devolverDestino() {
    	return this.destino;
    }
    
    public int devolverPeso() {
    	return this.peso;
    }
    
    public int devolverOrigen() {
    	return this.origen;
    }
}