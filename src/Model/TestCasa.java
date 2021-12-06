package Model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCasa {

	@Test
	public void testCasa() {
		int[] viz = new int[] {1, 2, 3};
		Casa c = new Casa(0, viz, 1, 2,true);
		assertNotNull(c);
	}
	
	@Test
	public void testGetCasa() {
		int[] viz = new int[] {1, 2, 3};
		Casa c = new Casa(0, viz, 1, 2,true);
		Casa[] lista = c.getCasa(null, null);
		assertNotNull(lista);
	}
	
	@Test
	public void testGet() {
		int[] viz = new int[] {1, 2, 3};
		Casa c = new Casa(12, viz, 1, 2,true);
		assertSame(c.get(), 12);
		assertNotNull(c);
	}
	
	@Test
	public void testSetQtPeao() {
		int[] viz = new int[] {1, 2, 3};
		Casa c = new Casa(0, viz, 1, 2,true);
		assertSame(c.get(), 0);
		c.setQtPeao(23);
		assertSame(c.get(), 23);
		assertNotNull(c);
	}
	
	@Test
	public void testGetFicha() {
		int[] viz = new int[] {1, 2, 3};
		Casa c = new Casa(0, viz, 1, 2,true);
		assertTrue(c.getFicha());
		Casa c2 = new Casa(0, viz, 1, 2, false);
		assertFalse(c2.getFicha());
		assertNotNull(c);
		assertNotNull(c2);
	}
	
	@Test
	public void testRemoveFicha() {
		int[] viz = new int[] {1, 2, 3};
		Casa c = new Casa(0, viz, 1, 2,true);
		assertTrue(c.getFicha());
		c.removeFicha();
		assertFalse(c.getFicha());
		assertNotNull(c);
	}
	
	@Test
	public void testGetVizinhos() {
		int[] viz = new int[] {1, 2, 3};
		Casa c = new Casa(0, viz, 1, 2,true);
		assertSame(c.getVizinhos(), viz);
		assertNotNull(c);
	}
	
	@Test
	public void testGetX() {
		int[] viz = new int[] {1, 2, 3};
		Casa c = new Casa(0, viz, 1, 2,true);
		assertSame(c.getX(), 1);
		assertNotNull(c);
	}
	
	@Test
	public void testGetY() {
		int[] viz = new int[] {1, 2, 3};
		Casa c = new Casa(0, viz, 1, 2,true);
		assertSame(c.getY(), 2);
		assertNotNull(c);
	}
}
