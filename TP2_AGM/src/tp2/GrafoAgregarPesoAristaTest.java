package tp2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Assert.*;

public class GrafoAgregarPesoAristaTest {
	
	private Grafo crearGrafo() {
		Grafo grafo = new Grafo(5);
		grafo.agregarVecinos(0, 1);
		grafo.agregarVecinos(4, 2);
		return grafo;
	}

	@Test(expected = IllegalArgumentException.class)
	public void pesoEnVerticeFueraDeRangoTest() {
		Grafo grafo = crearGrafo();
		grafo.agregarPesoArista(6, 0, 10);
	}
	
	@Test
	public void pesoEnVerticesNoVecinosTest() {
		Grafo grafo = crearGrafo();
		assertEquals(-1, grafo.pesoArista(4, 3, 10));
	}
	
	@Test
	public void agregarPeso0FalseTest() {
		Grafo grafo = crearGrafo();
		assertEquals(-2, grafo.pesoArista(0, 1, 0));
	}
	
	@Test
	public void agregarPesoAristaOkTest() {
		Grafo grafo = crearGrafo();
		assertEquals(1, grafo.pesoArista(4, 2, 5));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void pesoEnVerticeNegativoTest() {
		Grafo grafo = crearGrafo();
		grafo.pesoArista(-1, 0, 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void pesoEnMismoVerticeFalseTest() {
		Grafo grafo = crearGrafo();
		grafo.pesoArista(1, 1, 25);
	}
}
