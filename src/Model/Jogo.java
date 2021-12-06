package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;

class Jogo{
	private int numJogadores;
	private Boolean naoTerminou = false;
	private List<Integer> vetorCartas;
	private int[] pontuacao = null;
	private int vencedor;
	private int jogadorDaVez;
	private int indiceCarta;
	List<ObservadorIF> lob=new ArrayList<ObservadorIF>();
	
	Jogo(int numJogadores){
		this.numJogadores = numJogadores;
		this.jogadorDaVez = 0;
		this.indiceCarta = 0;
		embaralhaLista();

	}
	
	
	private void embaralhaLista() {
		this.vetorCartas = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
		Collections.shuffle(this.vetorCartas);
	}
	
	List<Integer> retornaListaCartas() {
		return this.vetorCartas;
	}
	
	int[] getPontuacao() {
		if(this.pontuacao == null) {
			this.pontuacao = new int[4];
			this.pontuacao[0] = 0;
			this.pontuacao[1] = 0;
			this.pontuacao[2] = 0;
			this.pontuacao[3] = 0;
		}
		return this.pontuacao;
	}
	
	int getVencedor() {
		if(this.naoTerminou == false) {
			return this.vencedor;
		}else {
			return -1;
		}
	}
	
	int getNumJogadores() {
		return this.numJogadores;
	}
	
	void addPontuacao(int jogador) {
		if(this.pontuacao == null) {
			this.pontuacao = new int[4];
			this.pontuacao[0] = 0;
			this.pontuacao[1] = 0;
			this.pontuacao[2] = 0;
			this.pontuacao[3] = 0;
		}
		this.pontuacao[jogador]++;
	}
	void finalizaJogo() {
		int max = 0;
		int pMax = -1;
		for(int i = 0; i < this.numJogadores; i++) {
			if(this.pontuacao[i] > pMax) {
				max = i;
				pMax = this.pontuacao[i];
			}
		}
		int[] empate = new int[4];
		int count = 0;
		for(int i = 0; i < this.numJogadores; i++) {
			if(this.pontuacao[i] == pMax) {
				empate[count] = i;
				count++;
			}
		}
		if(count > 0) {
			this.vencedor = max;
		}else {
			this.vencedor = -1;
		}
		this.naoTerminou = false;
	}
	
	int getJogadorDaVez() {
		return this.jogadorDaVez;
	}
	void setJogadorDaVez(int i) {
		jogadorDaVez = i;
	}
	void passaVez() {
		if(this.numJogadores == 2) {
			if(this.jogadorDaVez == 1) {
				this.jogadorDaVez = 0;
			}else {
				this.jogadorDaVez = 1;
			}
		}else {
			if(this.jogadorDaVez == 3) {
				this.jogadorDaVez = 0;
			}else {
				this.jogadorDaVez++;
			}
		}
	}
	
	public int retiraCarta() {
		int carta = this.vetorCartas.get(this.indiceCarta);
		if(this.indiceCarta == 17) {
			this.indiceCarta = 0;
		}else {
			this.indiceCarta++;
		}
		return carta;
	}
	
	
}
