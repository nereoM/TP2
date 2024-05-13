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
	
	@Test (expected = IllegalArgumentException.class)
    public void menorACeroTest() {

        new UnionFind(-1);

    }
	
	@Test
    public void unionTransitivaTest() {

        UnionFind uf = new UnionFind(8);

        uf.union(1, 3);
        uf.union(5, 3);
        uf.union(2, 1);
        uf.union(7, 2);

        uf.union(0, 4);
        uf.union(6, 4);

        assertTrue(uf.find(7, 5));
        assertFalse(uf.find(7, 6));

        uf.union(3, 4);

        assertTrue(uf.find(7, 6));

    }

}