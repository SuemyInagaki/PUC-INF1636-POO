package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;
//implements ObservadoIF
class Peao{
	private int jogador;
	private int posicao;
	private int numPeao;
	private int inicio;
	private int fim;
	private int numCasasAndadas;
	private Boolean podeMover = true;
	protected List<ObservadorIF> lista = new ArrayList<ObservadorIF>();
	private static Peao inst[] = null;
	
	Peao(int jogador, int posicao, int numPeao, int numCasasAndadas, int inicio, int fim) {
		this.jogador = jogador;
		this.posicao = posicao;
		this.numPeao = numPeao;
		this.numCasasAndadas = numCasasAndadas;
		this.inicio = inicio;
		this.fim = fim;
	}
	
	static Peao[] getPeao(String filename, int qtPeoes, int[] jogadores, int[] inicioDeCadaPeao, int[] fimDeCadaPeao, int[] posicoes) {
		if(filename == null) {
			if(inst == null) {
				inst = new Peao[24];
				inst[0] = new Peao(0, 72, 1, 0, 72, 145);
				inst[1] = new Peao(0, 72, 2, 0, 72, 145);
				inst[2] = new Peao(0, 72, 3, 0, 72, 145);
				inst[3] = new Peao(0, 72, 4, 0, 72, 145);
				inst[4] = new Peao(0, 72, 5, 0, 72, 145);
				inst[5] = new Peao(0, 72, 6, 0, 72, 145);
				inst[6] = new Peao(1, 145, 1, 0, 145, 72);
				inst[7] = new Peao(1, 145, 2, 0, 145, 72);
				inst[8] = new Peao(1, 145, 3, 0, 145, 72);
				inst[9] = new Peao(1, 145, 4, 0, 145, 72);
				inst[10] = new Peao(1, 145, 5, 0, 145, 72);
				inst[11] = new Peao(1, 145, 6, 0, 145, 72);
				inst[12] = new Peao(2, 146, 1, 0, 146, 147);
				inst[13] = new Peao(2, 146, 2, 0, 146, 147);
				inst[14] = new Peao(2, 146, 3, 0, 146, 147);
				inst[15] = new Peao(2, 146, 4, 0, 146, 147);
				inst[16] = new Peao(2, 146, 5, 0, 146, 147);
				inst[17] = new Peao(2, 146, 5, 0, 146, 147);
				inst[18] = new Peao(3, 147, 1, 0, 147, 146);
				inst[19] = new Peao(3, 147, 2, 0, 147, 146);
				inst[20] = new Peao(3, 147, 3, 0, 147, 146);
				inst[21] = new Peao(3, 147, 4, 0, 147, 146);
				inst[22] = new Peao(3, 147, 5, 0, 147, 146);
				inst[23] = new Peao(3, 147, 6, 0, 147, 146);
			}
		}else {
			if(inst == null) {
				inst = new Peao[qtPeoes];
				for(int i = 0; i < qtPeoes; i++) {
					inst[i] = new Peao(jogadores[i], posicoes[i], 0, 0, inicioDeCadaPeao[i], fimDeCadaPeao[i]);
				}
			}
		}
		return inst;
	}
	
	static void apagaInst() {
		if(inst != null) {
			inst = null;
		}
	}
	
	int getJogador() {
		return jogador;
	}
	
	void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
	public int getPosicao() {
		return posicao;
	}
	
	void setNumPeao(int numPeao) {
		this.numPeao = numPeao;
	}
	
	int getNumPao() {
		return this.numPeao;
	}
	
	void setCasasAndadas(int numCasasAndadas){
		this.numCasasAndadas = numCasasAndadas;
	}
	
	int getCasasAndadas(){
		return numCasasAndadas;
	}
	
	int getInicio() {
		return this.inicio;
	}
	
	int getFim() {
		return this.fim;
	}
	
	static Peao[] removePeao(int index, int qtPeao) {
		if(inst != null) {
			int j = 0;
			Peao[] aux = new Peao[qtPeao-1];
			for(int i = 0; i < qtPeao; i++) {
				int jo = inst[i].getJogador();
				int p = inst[i].getPosicao();
				int np = inst[i].getNumPao();
				int nc = inst[i].getCasasAndadas();
				int ini = inst[i].getInicio();
				int fim = inst[i].getFim();
				
				if(i != index) {
					aux[j] = new Peao(jo, p, np, nc, ini, fim);
					j++;
				}
			}
			inst = aux;
		}
		return inst;
	}
	
	void changePodeMover() {
		if(this.podeMover == false) {
			this.podeMover = true;
		}else {
			this.podeMover = false;
		}
	}
	
	Boolean getPodeMover() {
		return this.podeMover;
	}
	
}
