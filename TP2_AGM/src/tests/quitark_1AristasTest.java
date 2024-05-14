package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import tp2.Arista;
import tp2.Grafo;

public class quitark_1AristasTest {
	
	private Grafo grafo = new Grafo(5);

	@Test(expected = IllegalArgumentException.class)
	public void quitarAristasListaVaciaTest() {
		List<Arista> aristas = new ArrayList<>(0);
		grafo.quitarK_1Aristas(aristas, 2);
	}
	
	@Test
	public void quitarAristasSizeCorrectoTest() {
		List<Arista> aristas = crearListaAristas();
		int cantRegiones = 3;
		assertEquals(aristas.size()-(cantRegiones-1), grafo.quitarK_1Aristas(aristas, 3).size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cantRegionesNegativaTest() {
		List<Arista> aristas = crearListaAristas();
		int cantRegiones = -1;
		grafo.quitarK_1Aristas(aristas, cantRegiones);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cantRegionesMayorASizeAristaTest() {
		List<Arista> aristas = crearListaAristas();
		int cantRegiones = 5;
		grafo.quitarK_1Aristas(aristas, cantRegiones);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cantRegionesIgualA0Test() {
		List<Arista> aristas = crearListaAristas();
		int cantRegiones = 0;
		grafo.quitarK_1Aristas(aristas, cantRegiones);
	}
	
	private List<Arista> crearListaAristas() {
		List<Arista> aristas = new ArrayList<>();
		
		aristas.add(new Arista(2, 1, 10));
		aristas.add(new Arista(3, 2, 5));
		aristas.add(new Arista(0, 2, 8));
		aristas.add(new Arista(4, 1, 9));
		
		return aristas;
	}
}
