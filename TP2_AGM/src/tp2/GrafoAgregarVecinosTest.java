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
		grafo.agregarVecinos(3, 3);
		grafo.agregarVecinos(0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarVecinosVerticeNegativoTest() {
		Grafo grafo = crearGrafo();
		grafo.agregarVecinos(-2, 4);
		grafo.agregarVecinos(1, -3);
		grafo.agregarVecinos(-0, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarVecinosFueraRangoTest() {
		Grafo grafo = crearGrafo();
		grafo.agregarVecinos(7, 3);
		grafo.agregarVecinos(2, 9);
		grafo.agregarVecinos(20, 10);
	}
	
	
}
