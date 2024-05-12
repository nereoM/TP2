package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import tp2.Arista;

public class Chequeos {
	
	public static void sonIguales(List<Arista> esperado, List<Arista> obtenido) {
		assertEquals(esperado.size(), obtenido.size());
		for(Arista a : obtenido) {
			assertTrue(obtenido.contains(a));
		}
	}

	public static void verticesMenosUnAristas(List<Arista> agm, int i) {
		assertEquals(agm.size(), i);
	}

}