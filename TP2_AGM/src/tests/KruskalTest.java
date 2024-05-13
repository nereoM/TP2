package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import tp2.Arista;
import tp2.Grafo;

//import grafos.Grafo;

public class KruskalTest {

	@Test
	public void grafoNoCompletoCorrectoTest() {
		
		Grafo grafo = crearGrafo();
		
		grafo.agregarPesoArista(0, 1, 2);
		grafo.agregarPesoArista(4, 2, 7);
		grafo.agregarPesoArista(0, 2, 5);
		grafo.agregarPesoArista(4, 3, 3);
		grafo.agregarPesoArista(3, 1, 18);
		grafo.agregarPesoArista(3, 2, 9);
		grafo.agregarPesoArista(0, 3, 1);
		
		List<Arista> esperado = new ArrayList<Arista>();
		esperado.add(new Arista(0, 3, 1));
		esperado.add(new Arista(0, 1, 2));
		esperado.add(new Arista(3, 4, 3));
		esperado.add(new Arista(0, 2, 5));
		
		List<Arista> agm = grafo.kruskal();
		
		Chequeos.sonIguales(esperado, agm);
		
	}
	
	@Test
	public void pesosNegativosCorrectoTest() {
		
		Grafo grafo = crearGrafo();
		
		grafo.agregarPesoArista(0, 1, -2);
		grafo.agregarPesoArista(4, 2, -7);
		grafo.agregarPesoArista(0, 2, -5);
		grafo.agregarPesoArista(4, 3, -3);
		grafo.agregarPesoArista(3, 1, -18);
		grafo.agregarPesoArista(3, 2, -9);
		grafo.agregarPesoArista(0, 3, -1);
		
		List<Arista> esperado = new ArrayList<Arista>();
		esperado.add(new Arista(0, 3, 1));
		esperado.add(new Arista(0, 1, 2));
		esperado.add(new Arista(3, 4, 3));
		esperado.add(new Arista(0, 2, 5));
		
		List<Arista> agm = grafo.kruskal();
		
		Chequeos.sonIguales(esperado, agm);
		
	}
	
	@Test
	public void verticesMenosUnAristasTest() {
		
		Grafo grafo = grafoCompleto();
		
		grafo.agregarPesoArista(0, 1, 2);
		grafo.agregarPesoArista(0, 2, 7);
		grafo.agregarPesoArista(0, 3, 5);
		grafo.agregarPesoArista(1, 2, 3);
		grafo.agregarPesoArista(1, 3, 18);
		grafo.agregarPesoArista(2, 3, 9);
		
		List<Arista> agm = grafo.kruskal();
		
		Chequeos.verticesMenosUnAristas(agm, grafo.tamano() - 1);
		
	}
	
	@Test
	public void grafoSinAristasTest() {
		Grafo grafo = new Grafo(5);
		
		List<Arista> agm = grafo.kruskal();
		
		assertEquals(agm.size(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void grafoVaciotest() {
		Grafo grafo = new Grafo(0);
	}
	
	private Grafo crearGrafo() {
		
		Grafo grafo = new Grafo(5);
		
		grafo.agregarVecinos(0, 1);
		grafo.agregarVecinos(4, 2);
		grafo.agregarVecinos(0, 2);
		grafo.agregarVecinos(4, 3);
		grafo.agregarVecinos(3, 1);
		grafo.agregarVecinos(3, 2);
		grafo.agregarVecinos(0, 3);
		
		return grafo;
	}
	
	private Grafo grafoCompleto() {
		
		Grafo grafo = new Grafo(4);
		
		grafo.agregarVecinos(0, 1);
		grafo.agregarVecinos(0, 2);
		grafo.agregarVecinos(0, 3);
		grafo.agregarVecinos(1, 2);
		grafo.agregarVecinos(1, 3);
		grafo.agregarVecinos(2, 3);
		
		return grafo;
	}
	
//	private Grafo aleatorio() {
//	Random random2 = new Random();
//	int numeroAleatorio = random2.nextInt(10) + 1; 
//	Grafo grafo = new Grafo(numeroAleatorio);
//	Random random = new Random(0);
//	
//	for (int i = 0; i < numeroAleatorio; i++) {
//		for (int j = i+1; j < numeroAleatorio; j++) {
//			if (random.nextDouble() < 0.3) {
//				grafo.agregarVecinos(i, j);
//			}
//		}
//	}
//	return grafo;
//}
//
//private Grafo pesosAleatorio() {
//	Grafo grafo = aleatorio();
//	boolean[][] matrizAdy = grafo.devolverMatrizAdy();
//	int[][] matrizPesos = grafo.devolverMatrizPesos();
//	for (int i = 0; i < matrizAdy.length; i++) {
//		for (int j = 0; j < matrizAdy[i].length; j++) {
//			if (matrizAdy[i][j] && matrizPesos[i][j] == 0) {
//				Random random = new Random();
//				int pesoAleatorio = random.nextInt(100) + 1;
//				matrizPesos[i][j] = pesoAleatorio;
//			}
//		}
//	}
//	return grafo;
//}

}