package tp2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Assert.*;

public class GrafoAgregarVecinosTest {


	private Grafo crearGrafo() {
		Grafo grafo = new Grafo(5);
		return grafo;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarVecinosMismoVerticeTest() {
		Grafo grafo = crearGrafo();
		grafo.agregarVecinos(1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarVecinosVerticeNegativoTest() {
		Grafo grafo = crearGrafo();
		grafo.agregarVecinos(-2, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarVecinosFueraRango() {
		Grafo grafo = crearGrafo();
		grafo.agregarVecinos(7, 5);
	}
	
	
}
