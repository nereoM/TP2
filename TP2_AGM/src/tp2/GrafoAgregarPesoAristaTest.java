package tp2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Assert.*;

public class GrafoAgregarPesoAristaTest {
	
	private Grafo crearGrafo() {
		Grafo grafo = new Grafo(24);
		return grafo;
	}

	@Test(expected = IllegalArgumentException.class)
	public void pesoEnVerticeFueraDeRangoTest() {
		crearGrafo().agregarPesoArista(25, 5, 10);
	}
	
	@Test
	public void pesoEnVerticesNoVecinosTest() {
		assertEquals(-1, crearGrafo().pesoArista(0, 3, 10));
	}
	
	@Test
	public void agregarPeso0FalseTest() {
		assertEquals(-2, crearGrafo().pesoArista(15, 18, 0));
	}
	
	@Test
	public void agregarPesoAristaOk() {
		assertEquals(1, crearGrafo().pesoArista(15, 18, 5));
	}
}
