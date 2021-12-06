package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class TestJogo {

	@Test
	void testJogo() {
		Jogo j = new Jogo(2);
		assertNotNull(j);
	}
	
	@Test
	void testEmbaralhaLista() {
		Jogo j = new Jogo(2);
		List<Integer> vetor = j.retornaListaCartas();
		Jogo j2 = new Jogo(2);
		List<Integer> vetor2 = j2.retornaListaCartas();
		assertNotSame(vetor, vetor2);
		assertNotNull(j);
		assertNotNull(j2);
	}
	
	@Test
	void testRetornaListaCartas() {
		Jogo j = new Jogo(2);
		List<Integer> vetor = j.retornaListaCartas();
		assertNotNull(j);
		assertNotNull(vetor);
	}
	
	@Test
	void testGetPontuacao() {
		Jogo j = new Jogo(2);
		int[] pontuacao = j.getPontuacao();
		assertNotNull(j);
		assertNotNull(pontuacao);
	}
	
	@Test
	void testGetVencedor() {
		Jogo j = new Jogo(2);
		int vencedor = j.getVencedor();
		assertNotNull(j);
		assertNotNull(vencedor);
	}
	
	@Test
	void testGetNumJogadores() {
		Jogo j = new Jogo(2);
		int n = j.getNumJogadores();
		assertNotNull(j);
		assertSame(n, 2);
	}
	
	@Test
	void testAddPontuacao() {
		Jogo j = new Jogo(2);
		int[] pontuacao = j.getPontuacao();
		assertNotNull(j);
		assertNotNull(pontuacao);
		j.addPontuacao(0);
		pontuacao = j.getPontuacao();
		assertSame(pontuacao[0], 1);
	}
	
	@Test
	void testGetJogadorDaVez() {
		Jogo j = new Jogo(2);
		int n = j.getJogadorDaVez();
		assertNotNull(j);
		assertSame(n, 0);
	}
	
	@Test
	void testSetJogadorDaVez() {
		Jogo j = new Jogo(2);
		int n = j.getJogadorDaVez();
		assertNotNull(j);
		assertSame(n, 0);
		j.setJogadorDaVez(1);
		n = j.getJogadorDaVez();
		assertNotNull(j);
		assertSame(n, 1);
	}
	
	@Test
	void testPassaVez() {
		Jogo j = new Jogo(2);
		int n = j.getJogadorDaVez();
		assertNotNull(j);
		assertSame(n, 0);
		j.passaVez();
		n = j.getJogadorDaVez();
		assertNotNull(j);
		assertSame(n, 1);
	}
	
	@Test
	void testRetiraCarta() {
		Jogo j = new Jogo(2);
		assertNotNull(j);
		assertNotNull(j.retiraCarta());
	}
}
