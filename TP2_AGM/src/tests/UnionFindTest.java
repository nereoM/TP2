package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import tp2.UnionFind;

public class UnionFindTest {

	@Test (expected = IllegalArgumentException.class)
	public void unionFindVaciaTest() {
		
		UnionFind uf = new UnionFind(0);
		
	}
	
	@Test
	public void unicoVertice() {
		
		UnionFind uf = new UnionFind(1);
		
		assertEquals(0, uf.raiz(0));
		assertTrue(uf.find(0,0));
		
	}
	
	@Test
	public void verticesDesconectados() {
		
		UnionFind uf = new UnionFind(5);
		
		assertFalse(uf.find(1, 4));

	}
	
	@Test
	public void unionSimple() {
		
		UnionFind uf = new UnionFind(5);
		
		uf.union(2, 4);
		assertTrue(uf.find(2, 4));
		assertEquals(uf.raiz(2), uf.raiz(4));
		
	}
	
	@Test
	public void unionTransitiva() {
		
		UnionFind uf = new UnionFind(10);
		
		uf.union(0, 1);
	    uf.union(1, 2);
	    uf.union(3, 4);
	    uf.union(4, 5);
	    uf.union(6, 7);
	    uf.union(7, 8);
		
	 // Verify that elements within each component are connected
        assertEquals(true, uf.find(0, 2));
        assertEquals(true, uf.find(3, 5));
        assertEquals(true, uf.find(6, 8));

        // Verify that elements from different components are not connected
        assertEquals(false, uf.find(0, 3));
        assertEquals(false, uf.find(1, 6));
        assertEquals(false, uf.find(2, 8));

        // Perform a transitive union to connect components {0, 1, 2} and {6, 7, 8}
        uf.union(2, 6);

        // Verify that all elements are now connected in a single component
        assertEquals(true, uf.find(0, 8));
        assertEquals(true, uf.find(1, 7));
        assertEquals(true, uf.find(2, 5));
        assertEquals(true, uf.find(3, 6));
        assertEquals(true, uf.find(4, 8));
		
	}

}