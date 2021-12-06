package Model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPeao {

	@Test
	public void testPeao() {
		Peao p = new Peao(0, 0, 0, 0, 0, 0);
		assertNotNull(p);
		
	}
	
	@Test
	public void testGetJogador() {
		Peao p = new Peao(0, 0, 0, 0, 0, 0);
		assertSame(p.getJogador(), 0);
	}
	
	@Test
	public void testSetPosicao() {
		Peao p = new Peao(0, 0, 0, 0, 0, 0);
		assertSame(p.getPosicao(), 0);
		p.setPosicao(30);
		assertSame(p.getPosicao(), 30);
	}
	
	@Test
	public void testGetPosicao() {
		Peao p = new Peao(0, 12, 0, 0, 0, 0);
		assertSame(p.getPosicao(), 12);
	}
	
	@Test
	public void testSetNumPeao() {
		Peao p = new Peao(0, 0, 0, 0, 0, 0);
		assertSame(p.getNumPao(), 0);
		p.setNumPeao(24);
		assertSame(p.getNumPao(), 24);
	}
	
	@Test
	public void testGetNumPeao() {
		Peao p = new Peao(0, 0, 0, 0, 0, 0);
		assertSame(p.getNumPao(), 0);
	}
	
	@Test
	public void testGetInicio() {
		Peao p = new Peao(0, 0, 0, 0, 33, 0);
		assertSame(p.getInicio(), 33);
	}
	
	@Test
	public void testGetFim() {
		Peao p = new Peao(0, 0, 0, 0, 0, 22);
		assertSame(p.getFim(), 22);
	}
	
	@Test
	public void testRemovePeao() {
		Peao p1 = new Peao(0, 0, 0, 0, 1, 22);
		Peao[] lista = p1.getPeao(null, 0, null, null, null, null);
		assertSame(lista.length, 24);
		lista = p1.removePeao(0, 24);
		assertSame(lista.length, 23);
	}
	
	
}
