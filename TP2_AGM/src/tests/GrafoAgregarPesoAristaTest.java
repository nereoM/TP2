package tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Assert.*;

import tp2.Grafo;

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
		grafo.agregarPesoAristaAux(6, 0, 10);
	}
	
	@Test
	public void pesoEnVerticesNoVecinosTest() {
		Grafo grafo = crearGrafo();
		assertEquals(-1, grafo.agregarPesoArista(4, 3, 10));
	}
	
	@Test
	public void agregarPeso0FalseTest() {
		Grafo grafo = crearGrafo();
		assertEquals(-2, grafo.agregarPesoArista(0, 1, 0));
	}
	
	@Test
	public void agregarPesoAristaOkTest() {
		Grafo grafo = crearGrafo();
		assertEquals(1, grafo.agregarPesoArista(4, 2, 5));
	}
	
	@Test
	public void pesoEnVerticeNegativoTest() {
		Grafo grafo = crearGrafo();
		assertEquals(2, grafo.agregarPesoArista(-1, 0, 1));
	}
	
	@Test
	public void pesoEnMismoVerticeFalseTest() {
		Grafo grafo = crearGrafo();
		assertEquals(2, grafo.agregarPesoArista(1, 1, 25));
	}
}
