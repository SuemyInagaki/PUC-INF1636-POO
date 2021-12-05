package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;

public class APIModel implements ObservadoIF{
	private static APIModel api=null;
	private Casa[] casas;
	private Peao[] peoes;
	private Dado dado = Dado.getDado();
	private int numJogadores;
	int selected = -1;
	private Jogo jogo = null;
	private int[][] casasComPeoes;
	int[] dadosRodados;
	int[][] vizinhos;
	int[] pontuacao;
	int jogadorDaVez = 0;
	int[] casaSelecionada;
	int[] corDosPeoes = null;
	int ultimoPeaoSelecionado = -1;
	boolean firstClick = false;
	int movimentosRestantes = 0;
	int[] qtPeaoCadaJogador = null;
	boolean jogadorJaRolouDado = false;
	boolean fimDeJogo = false;
	int vencedor = -1;
	int qtPeoes;
	boolean temFicha = false;
	int indiceCarta = -1;
	int cartaImplementada = -1;
	int indiceDadoColorido = -1;
	int[] valoresDadoColorido = new int[] {0, 1, 2, 3, 4, 5};
	boolean podeRolarDadoColorido = false;
	int[] indiceDasCasasComFichas = null;
	int[] posicoesDosPeoes = null;
	int[] inicioDeCadaPeao = null;
	int[] fimDeCadaPeao = null;
	List<ObservadorIF> lob=new ArrayList<ObservadorIF>();
	
	public static APIModel getApiModel() {
		if(api==null)
			api=new APIModel();
		return api;	
	}
	public void addObserver(ObservadorIF o) {
		lob.add(o);
	}
	
	public void removeObserver(ObservadorIF o) {
		lob.remove(o);
	}
	
	public Object get() {
		Object dados[]=new Object[22];
		
		dados[0]=this.numJogadores;
		dados[1]=this.casasComPeoes;
		dados[2]=this.getJogadorDaVez();
		dados[3]=this.dadosRodados;
		dados[4]=this.selected;
		dados[5]=this.vizinhos;
		dados[6]=this.casaSelecionada;
		dados[7]=this.movimentosRestantes;
		dados[8]=this.pontuacao;
		dados[9]=this.qtPeaoCadaJogador;
		dados[10]=this.qtPeoes;
		dados[11]=this.corDosPeoes;
		dados[12]=this.fimDeJogo;
		dados[13]=this.vencedor;
		dados[14]=this.temFicha;
		dados[15]=this.indiceCarta;
		dados[16]=this.cartaImplementada;
		dados[17]=this.indiceDadoColorido;
		dados[18]=this.indiceDasCasasComFichas;
		dados[19]=this.posicoesDosPeoes;
		dados[20]=this.inicioDeCadaPeao;
		dados[21]=this.fimDeCadaPeao;
		return dados;
	}
	public int[][] novoJogo(String filename) {
		this.jogo = null;
		Peao.apagaInst();
		Casa.apagaInst();
		casas = null;
		peoes = null;
		selected = -1;
		casasComPeoes = null;
		dadosRodados = null;
		vizinhos = null;
		pontuacao = null;
		jogadorDaVez = 0;
		casaSelecionada = null;
		corDosPeoes = null;
		ultimoPeaoSelecionado = -1;
		firstClick = false;
		movimentosRestantes = 0;
		qtPeaoCadaJogador = null;
		jogadorJaRolouDado = false;
		fimDeJogo = false;
		vencedor = -1;
		qtPeoes = 0;
		temFicha = false;
		indiceDadoColorido = -1;
		indiceDasCasasComFichas = null;
		posicoesDosPeoes = null;
		inicioDeCadaPeao = null;
		fimDeCadaPeao = null;
		if(filename == null) {
			if(this.jogo == null) {
				this.jogo = new Jogo(this.numJogadores);
			}
			casas = Casa.getCasa(null, null);
			peoes = Peao.getPeao(null, 0, null, null, null, null);
			this.casasComPeoes = this.getCasasComPeoes();
			this.pontuacao = jogo.getPontuacao();
			this.qtPeoes = this.numJogadores*6;
			this.qtPeaoCadaJogador = new int[this.numJogadores];
			for(int i = 0; i < this.numJogadores; i++) {
				this.qtPeaoCadaJogador[i] = 6;
			}
			this.corDosPeoes = new int[this.qtPeoes];
			for(int i = 0; i < this.qtPeoes; i++) {
				this.corDosPeoes[i] = this.peoes[i].getJogador();
			}
			this.indiceDasCasasComFichas = getCasasComFichas();
			this.posicoesDosPeoes = getPosicoesDosPeoes();
			this.inicioDeCadaPeao = getInicioDeCadaPeao();
			this.fimDeCadaPeao = getFimDeCadaPeao();
			for(ObservadorIF o:lob)
				o.notify(this);
			return this.casasComPeoes;
		}else {
			carregaJogoSalvo(filename);
			if(this.jogo == null) {
				this.jogo = new Jogo(this.numJogadores);
			}
			peoes = Peao.getPeao("SIM", qtPeoes, corDosPeoes, inicioDeCadaPeao, fimDeCadaPeao, posicoesDosPeoes);
			casas = Casa.getCasa("SIM", indiceDasCasasComFichas);			
			for(ObservadorIF o:lob)
				o.notify(this);
		}
		return this.casasComPeoes;
	}
	private int[] getInicioDeCadaPeao() {
		int q = 0;
		int[] aux = new int[qtPeoes];
		for(int i = 0; i < qtPeoes; i++) {
			int p = peoes[i].getInicio();
			aux[q] = p;
			q++;
		}
		return aux;
	}
	private int[] getFimDeCadaPeao() {
		int q = 0;
		int[] aux = new int[qtPeoes];
		for(int i = 0; i < qtPeoes; i++) {
			int p = peoes[i].getFim();
			aux[q] = p;
			q++;
		}
		return aux;
	}
	
	private int[] getPosicoesDosPeoes(){
		int q = 0;
		
		int[] aux = new int[qtPeoes];
		for(int i = 0; i < qtPeoes; i++) {
			int p = peoes[i].getPosicao();
			aux[q] = p;
			q++;
		}
		return aux;
	}
	private int[] getCasasComFichas(){
		int q = 0;
		for(int i = 0; i < casas.length; i++) {
			if(casas[i].getFicha()) {
				q++;
			}
		}
		int[] aux = new int[q];
		q = 0;
		for(int i = 0; i < casas.length; i++) {
			if(casas[i].getFicha()) {
				aux[q] = i;
				q++;
			}
		}
		return aux;
	}
	
	public boolean acabouOJogo() {
		for(int i = 0; i < this.numJogadores; i++) {
			if(this.qtPeaoCadaJogador[i] == 0) {
				return true;
			}
		}
		return false;
	}
	
	public int[] rodaDados() {
		if(!jogadorJaRolouDado) {
			podeRolarDadoColorido = false;
			int d1 = dado.geraValor();
			int d2 = dado.geraValor();
			dadosRodados = new int[2];
			dadosRodados[0] = d1;
			dadosRodados[1] = d2;
			movimentosRestantes = d1 + d2;
			if(d1 == d2) {
				podeRolarDadoColorido = true;
				indiceDadoColorido = rodaDadoColorido();
			}
			this.indiceDasCasasComFichas = getCasasComFichas();
			this.posicoesDosPeoes = getPosicoesDosPeoes();
			this.inicioDeCadaPeao = getInicioDeCadaPeao();
			this.fimDeCadaPeao = getFimDeCadaPeao();
			for(ObservadorIF o:lob)
				o.notify(this);
			jogadorJaRolouDado = true;
		}
		return dadosRodados;
	}
	public boolean verificaSeSelecionouVizinho(int origem, int x, int y) {
		int indice, cx, cy;
		for(int i = 0; i < casas.length; i++) {
			cx = casas[i].getX();
			cy = casas[i].getY();
			if(verificaSeEhNoCirculo(cx, cy, 20, x, y) == true) {
				if(ehVizinho(origem, i, casas)==true) {
					return true;
				}
			}
		}
		this.indiceDasCasasComFichas = getCasasComFichas();
		this.posicoesDosPeoes = getPosicoesDosPeoes();
		this.inicioDeCadaPeao = getInicioDeCadaPeao();
		this.fimDeCadaPeao = getFimDeCadaPeao();
		for(ObservadorIF o:lob)
			o.notify(this);
		return false;
	}
	
	
	public void verificaClique(int x, int y) {
		int indice, cx, cy;
		boolean selecionouPeao = false;
		boolean selecionouVizinho = false;
		if(firstClick == true) {
			firstClick = false;
		}
		else {
			firstClick = true;
		}
		selected = -1;
		int aux = -1;
		for(int i = 0; i < this.qtPeoes; i++) {
			indice = peoes[i].getPosicao();
			cx = casas[indice].getX();
			cy = casas[indice].getY();
			if(verificaSeEhNoCirculo(cx, cy, 20, x, y) == true) {
				aux = i;
//				this.vizinhos = getCasasVizinhas(i);
				selecionouPeao = true;
			}
		}
		//if Primeiro clique e selecionou peao
		if(firstClick && selecionouPeao && peoes[aux].getJogador() == this.jogadorDaVez) {
			selected = aux;
			ultimoPeaoSelecionado = selected;
			this.vizinhos = getCasasVizinhas(selected);
			this.indiceDasCasasComFichas = getCasasComFichas();
			this.posicoesDosPeoes = getPosicoesDosPeoes();
			this.inicioDeCadaPeao = getInicioDeCadaPeao();
			this.fimDeCadaPeao = getFimDeCadaPeao();
			for(ObservadorIF o:lob)
				o.notify(this);
		}
		else if(!firstClick && ultimoPeaoSelecionado == selected && selecionouPeao) {
			selected = -1;
			ultimoPeaoSelecionado = -1;
			this.vizinhos = null;
			this.indiceDasCasasComFichas = getCasasComFichas();
			this.posicoesDosPeoes = getPosicoesDosPeoes();
			this.inicioDeCadaPeao = getInicioDeCadaPeao();
			this.fimDeCadaPeao = getFimDeCadaPeao();
			for(ObservadorIF o:lob)
				o.notify(this);
		}
		else if(!selecionouPeao && !firstClick && ultimoPeaoSelecionado!=-1) {
			indice = peoes[ultimoPeaoSelecionado].getPosicao();
			int indiceCasaSelecionada = -1;
			int[] viz = casas[indice].getVizinhos();
			for(int i = 0; i < viz.length; i++) {
				
				cx = casas[viz[i]].getX();
				cy = casas[viz[i]].getY();
				if(verificaSeEhNoCirculo(cx, cy, 20, x, y) == true) {
					casaSelecionada = new int[]{cx, cy};
					indiceCasaSelecionada = viz[i];
					this.vizinhos = null;
					selecionouPeao = false;
					selecionouVizinho = true;
				}
			}
			if(!firstClick && selecionouVizinho) {
				int peao = peoes[ultimoPeaoSelecionado].getNumPao();
				int origem = peoes[ultimoPeaoSelecionado].getPosicao();
				int destino = indiceCasaSelecionada;
				System.out.printf("Estou movendo o peao %d, do jogador %d\n", ultimoPeaoSelecionado, peoes[ultimoPeaoSelecionado].getJogador());
				movimentaUmaCasa(ultimoPeaoSelecionado, jogadorDaVez, origem, destino);
				casasComPeoes = getCasasComPeoes();
				this.indiceDasCasasComFichas = getCasasComFichas();
				this.posicoesDosPeoes = getPosicoesDosPeoes();
				this.inicioDeCadaPeao = getInicioDeCadaPeao();
				this.fimDeCadaPeao = getFimDeCadaPeao();
				for(ObservadorIF o:lob)
					o.notify(this);
				if(movimentosRestantes == 0) {
					verificaSeAlgumaCasaTemFicha();
				}
				if(movimentosRestantes == 0) {
					jogo.passaVez();
					jogadorJaRolouDado = false;
					jogadorDaVez = jogo.getJogadorDaVez();
					this.fimDeJogo = acabouOJogo();
					if(this.fimDeJogo) {
						jogo.finalizaJogo();
						this.vencedor = jogo.getVencedor();
					}
				}
				this.pontuacao = jogo.getPontuacao();
			}
		}
		casasComPeoes = getCasasComPeoes();
		this.indiceDasCasasComFichas = getCasasComFichas();
		this.posicoesDosPeoes = getPosicoesDosPeoes();
		this.inicioDeCadaPeao = getInicioDeCadaPeao();
		this.fimDeCadaPeao = getFimDeCadaPeao();
		for(ObservadorIF o:lob)
			o.notify(this);	
	}
	
	public int rodaDadoColorido() {
		if(podeRolarDadoColorido) {
			Random geraValor = new Random();
			this.indiceDadoColorido = geraValor.nextInt(6);
			// tirou a propria cor
			if(indiceDadoColorido == this.jogadorDaVez) {
				// leva o proprio peao para o fim, ele faz isso com o primeiro peao do jogador
				for(int i = 0; i < this.qtPeoes; i++) {
					if(peoes[i].getJogador() == this.jogadorDaVez) {
						peoes = Peao.removePeao(i, this.qtPeoes);
						qtPeoes--;
						this.qtPeaoCadaJogador[this.jogadorDaVez]--;
						this.corDosPeoes = new int[this.qtPeoes];
						for(int j = 0; j < this.qtPeoes; j++) {
							this.corDosPeoes[j] = this.peoes[j].getJogador();
						}
						this.jogo.addPontuacao(this.jogadorDaVez);
						this.pontuacao = jogo.getPontuacao();
						this.indiceDasCasasComFichas = getCasasComFichas();
						this.posicoesDosPeoes = getPosicoesDosPeoes();
						for(ObservadorIF o:lob)
							o.notify(this);
						break;
					}
				}
			}
			// tirou a cor de algum adversario
			else if(this.indiceDadoColorido < this.numJogadores) {
				// retorna o peao do adversario para o inicio. Escolhe o primeiro peao do adversario
				for(int i = 0; i < this.qtPeoes; i++) {
					if(peoes[i].getJogador() == this.indiceDadoColorido) {
						peoes[i].setPosicao(peoes[i].getInicio());
						this.indiceDasCasasComFichas = getCasasComFichas();
						this.posicoesDosPeoes = getPosicoesDosPeoes();
						this.inicioDeCadaPeao = getInicioDeCadaPeao();
						this.fimDeCadaPeao = getFimDeCadaPeao();
						for(ObservadorIF o:lob)
							o.notify(this);
						break;
					}
				}
			}
			return this.indiceDadoColorido;
		}
		return -1;
	}
	
	private boolean verificaSeAlgumaCasaTemFicha(){
		for(int i = 0; i < this.qtPeoes; i++) {
			int indice = peoes[i].getPosicao();
			// um peao do jogador esta em uma casa com ficha, e tem mais de um peao na casa
//			if(peoes[i].getJogador() == this.jogadorDaVez && casas[indice].getFicha() && casas[indice].get() >= 2) {
			if(peoes[i].getJogador() == this.jogadorDaVez && casas[indice].getFicha()) {
				casas[indice].removeFicha();
				this.temFicha = true;
				this.indiceCarta = getIndiceCarta();
				// vou implementar so essas duas cartas
				if(indiceCarta == 0) {
					cartaImplementada = 0;
					this.movimentosRestantes+=6;
					this.indiceDasCasasComFichas = getCasasComFichas();
					this.posicoesDosPeoes = getPosicoesDosPeoes();
					this.inicioDeCadaPeao = getInicioDeCadaPeao();
					this.fimDeCadaPeao = getFimDeCadaPeao();
					for(ObservadorIF o:lob)
						o.notify(this);
					
				}else if(indiceCarta == 1) {
					cartaImplementada = 1;
					this.movimentosRestantes+=3;
					this.indiceDasCasasComFichas = getCasasComFichas();
					this.posicoesDosPeoes = getPosicoesDosPeoes();
					this.inicioDeCadaPeao = getInicioDeCadaPeao();
					this.fimDeCadaPeao = getFimDeCadaPeao();
					for(ObservadorIF o:lob)
						o.notify(this);
				}
				return true;
			}
		}
		return false;
	}
	public boolean verificaSeEhNoCirculo(int cx, int cy, int raio, int x, int y) {
		if((x-cx)*(x-cx) + (y-cy)*(y-cy) <= raio*raio) {
			return true;
		}
		return false;
	}
	
	public void register(ObservadorIF o) {
		this.addObserver(o);
	}
	public void setNumJogadores(int q) {
		this.numJogadores = q;
	}
	public int getNumJogadores() {
		return this.jogo.getNumJogadores();
	}
	
	public int getJogadorDaVez() {
		return this.jogo.getJogadorDaVez();
	}
	
	public int getIndiceCarta() {
		return this.jogo.retiraCarta();
	}
	
	public int getVencedor() {
		return this.jogo.getVencedor();
	}
	
	public int[] getPontuacao() {
		return this.jogo.getPontuacao();
	}
	
	
	private Boolean ehVizinho(int origem, int destino, Casa[] casas) {
		int[] vizinhos = this.casas[origem].getVizinhos();
		for(int i = 0; i < vizinhos.length; i++) {
			if(vizinhos[i] == destino) {
				return true;
			}
		}
		return false;
	}
	
	private int[][] getCasasComPeoes(){
		int [][] posi;
		if(this.numJogadores == 2) {
			posi = new int[12][2];
		}
		else{
			posi = new int[24][2];
		}
		int indice;
		for(int i = 0; i < this.qtPeoes; i++) {
			indice = this.peoes[i].getPosicao();
			posi[i][0] = this.casas[indice].getX();
			posi[i][1] = this.casas[indice].getY();
		}
		return posi;
	}
	
	
	private int[][] getCasasVizinhas(int p){ // indice do peao
		int [][] posi;
		int indice = this.peoes[p].getPosicao();
		int tamanho = this.casas[indice].getVizinhos().length;
		posi = new int[tamanho][2];
		int[] vizinhos = this.casas[indice].getVizinhos();
		for(int i = 0; i < tamanho; i++) {
			posi[i][0] = this.casas[vizinhos[i]].getX();
			posi[i][1] = this.casas[vizinhos[i]].getY();
		}
		this.indiceDasCasasComFichas = getCasasComFichas();
		this.posicoesDosPeoes = getPosicoesDosPeoes();
		this.inicioDeCadaPeao = getInicioDeCadaPeao();
		this.fimDeCadaPeao = getFimDeCadaPeao();
		for(ObservadorIF o:lob)
			o.notify(this);
		return posi;
	}
		
	
	void movimentaUmaCasa(int peao, int jogador, int origem, int destino) {
//		System.out.printf("peao: %d, jogador: %d, jogador do peao: %d\n", peao, jogador, peoes[peao].getJogador());
//		System.out.printf("origem: %d, destino: %d\n", origem, destino);
//		for(int i = 0; i < this.numJogadores*6; i++) {
//			System.out.printf("peao: %d, posicao: %d, jogador: %d\n",i, peoes[i].getPosicao(), peoes[i].getJogador());
//		}
//		System.out.println("fim");
		
		if(this.peoes[peao].getPodeMover() == false) {
			System.out.println("Voce nao pode mover este peao");
			return;
		}
		if(this.movimentosRestantes == 0) {
			return;
		}
		// se o peao nao eh do jogador
		
		if(this.peoes[peao].getJogador() != jogador) {
			System.out.printf("O peao nao eh seu, peao: %d, jogador: %d, jogador do peao: %d\n", peao, jogador, peoes[peao].getJogador());
			return;
		}
		// se o destino nao eh vizinho
		if(this.ehVizinho(origem, destino, this.casas) == false) {
			System.out.println("Voce nao pode mover para essa casa");
			return;
		}
		// se o peao chegou no fim
		if(destino == this.peoes[peao].getFim()) {
			this.jogo.addPontuacao(jogador);
			this.peoes[peao].setPosicao(destino);
			this.peoes[peao].changePodeMover();
			int q = this.casas[destino].get();
			this.casas[destino].setQtPeao(q+1);
			q = this.casas[origem].get();
			this.casas[origem].setQtPeao(q-1);
			this.peoes = Peao.removePeao(peao, this.qtPeoes);
			this.qtPeoes--;
			this.qtPeaoCadaJogador[jogador]--;
			this.corDosPeoes = new int[this.qtPeoes];
			for(int i = 0; i < this.qtPeoes; i++) {
				this.corDosPeoes[i] = this.peoes[i].getJogador();
			}
			this.indiceDasCasasComFichas = getCasasComFichas();
			this.posicoesDosPeoes = getPosicoesDosPeoes();
			this.inicioDeCadaPeao = getInicioDeCadaPeao();
			this.fimDeCadaPeao = getFimDeCadaPeao();
			for(ObservadorIF o:lob)
				o.notify(this);
			movimentosRestantes--;
			this.casasComPeoes = this.getCasasComPeoes();
			this.indiceDasCasasComFichas = getCasasComFichas();
			this.posicoesDosPeoes = getPosicoesDosPeoes();
			this.inicioDeCadaPeao = getInicioDeCadaPeao();
			this.fimDeCadaPeao = getFimDeCadaPeao();
			for(ObservadorIF o:lob)
				o.notify(this);
			return;
		}
		// se a casa destino esta ocupada por um peao oponente
		if(this.casas[destino].get() == 1) {
			
			for(int i = 0; i < this.qtPeoes; i++) {
				if(this.peoes[i].getPosicao() == destino) { // o peao de indice i esta na casa destino
					if(this.peoes[i].getJogador() != jogador) { // esse peao nao eh do jogador, entao captura
						this.peoes[i].setPosicao(this.peoes[i].getInicio()); // mandou o peao oponente para o inicio dele
						int q = this.casas[origem].get();
						this.casas[origem].setQtPeao(q-1);
						this.peoes[peao].setPosicao(destino);
						movimentosRestantes--;
						this.casasComPeoes = this.getCasasComPeoes();
						this.indiceDasCasasComFichas = getCasasComFichas();
						this.posicoesDosPeoes = getPosicoesDosPeoes();
						this.inicioDeCadaPeao = getInicioDeCadaPeao();
						this.fimDeCadaPeao = getFimDeCadaPeao();
						for(ObservadorIF o:lob)
							o.notify(this);
						return;
					}
				}
			}
			
		}
		// se a casa destino esta ocupada por mais de um peao oponente
		if(this.casas[destino].get() > 1) {
			for(int i = 0; i < this.qtPeoes; i++) {
				if(this.peoes[i].getPosicao() == destino) { // o peao de indice i esta na casa destino
					if(this.peoes[i].getJogador() != jogador) { // esse peao nao eh do jogador, entao nao pode mover
						this.casasComPeoes = this.getCasasComPeoes();
						this.indiceDasCasasComFichas = getCasasComFichas();
						this.posicoesDosPeoes = getPosicoesDosPeoes();
						this.inicioDeCadaPeao = getInicioDeCadaPeao();
						this.fimDeCadaPeao = getFimDeCadaPeao();
						for(ObservadorIF o:lob)
							o.notify(this);
						return;
					}
				}
			}
		}
		// se a casa destino esta ocupada por peao proprio
		if(this.casas[destino].get() >= 1) {
			for(int i = 0; i < this.qtPeoes; i++) {
				if(this.peoes[i].getPosicao() == destino) { // o peao de indice i esta na casa destino
					if(this.peoes[i].getJogador() == jogador) { // esse peao eh do jogador
						int q = this.casas[origem].get();
						this.casas[origem].setQtPeao(q-1);
						q = this.casas[destino].get();
						this.casas[destino].setQtPeao(q+1);
						this.peoes[peao].setPosicao(destino);
						movimentosRestantes--;
						this.casasComPeoes = this.getCasasComPeoes();
						this.indiceDasCasasComFichas = getCasasComFichas();
						this.posicoesDosPeoes = getPosicoesDosPeoes();
						this.inicioDeCadaPeao = getInicioDeCadaPeao();
						this.fimDeCadaPeao = getFimDeCadaPeao();
						for(ObservadorIF o:lob)
							o.notify(this);
						return;
					}
				}
			}
		}
		// se a casa destino esta vazia
		if(this.casas[destino].get() == 0) {
			int q = this.casas[origem].get();
			this.casas[origem].setQtPeao(q-1);
			q = this.casas[destino].get();
			this.casas[destino].setQtPeao(q+1);
			this.peoes[peao].setPosicao(destino);
			movimentosRestantes--;
			this.casasComPeoes = this.getCasasComPeoes();
			this.indiceDasCasasComFichas = getCasasComFichas();
			this.posicoesDosPeoes = getPosicoesDosPeoes();
			this.inicioDeCadaPeao = getInicioDeCadaPeao();
			this.fimDeCadaPeao = getFimDeCadaPeao();
			for(ObservadorIF o:lob)
				o.notify(this);
			return;
		}
		this.casasComPeoes = this.getCasasComPeoes();
		for(ObservadorIF o:lob)
			o.notify(this);
	}
	void salvaJogo() {
		
	}
	
	void carregaJogoSalvo(String filename){
		try(BufferedReader file = new BufferedReader(new FileReader(filename))){
			String savedValue;
			// salva o numero de jogadores
			savedValue = file.readLine();
			System.out.println(savedValue);
			numJogadores = Integer.valueOf(savedValue);
			
			// salva a quantidade de peoes
			savedValue = file.readLine();
			System.out.println(savedValue);
			qtPeoes = Integer.valueOf(savedValue);
			
			// salva as coordenadas de cada peao;
			this.casasComPeoes = new int[qtPeoes][2];
			for(int i = 0; i < qtPeoes; i++) {
				savedValue = file.readLine();
				System.out.println(savedValue);
				casasComPeoes[i][0] = Integer.valueOf(savedValue);
				savedValue = file.readLine();
				System.out.println(savedValue);
				casasComPeoes[i][1] = Integer.valueOf(savedValue);
			}

			// salva o jogador da vez
			savedValue = file.readLine();
			System.out.println(savedValue);
			jogadorDaVez = Integer.valueOf(savedValue);
			
			// salva os dados
			dadosRodados = new int[2];
			savedValue = file.readLine();
			System.out.println(savedValue);
			dadosRodados[0] = Integer.valueOf(savedValue);
			savedValue = file.readLine();
			System.out.println(savedValue);
			dadosRodados[1] = Integer.valueOf(savedValue);
			
			// salva a quantidade de movimentos restantes
			savedValue = file.readLine();
			System.out.println(savedValue);
			movimentosRestantes = Integer.valueOf(savedValue);
			
			// salva a pontuacao
			pontuacao = new int[4];
			for(int i = 0; i < numJogadores; i++) {
				savedValue = file.readLine();
				System.out.println(savedValue);
				pontuacao[i] = Integer.valueOf(savedValue);
			}
			// salva a quantidade de peoes de cada jogador
			qtPeaoCadaJogador = new int[numJogadores];
			for(int i = 0; i < numJogadores; i++) {
				savedValue = file.readLine();
				System.out.println(savedValue);
				qtPeaoCadaJogador[i] = Integer.valueOf(savedValue);
			}
			
			// salva a cor dos peoes
			corDosPeoes = new int[qtPeoes];
			for(int i = 0; i < qtPeoes; i++) {
				savedValue = file.readLine();
				System.out.println(savedValue);
				corDosPeoes[i] = Integer.valueOf(savedValue);
			}
			
			// carrega se foi fim de jogo e o vencedor
			savedValue = file.readLine();
			if(savedValue == "true") {
				fimDeJogo = true;
				savedValue = file.readLine();
				vencedor = Integer.valueOf(savedValue);
			}
			else {
				fimDeJogo = false;
				vencedor = -1;
			}
			savedValue = file.readLine();
			int qtFichas = Integer.valueOf(savedValue);
			// carrega a lista de indice das casas com fichas
			indiceDasCasasComFichas = new int[qtFichas];
			for(int i = 0; i < qtFichas; i++) {
				savedValue = file.readLine();
				indiceDasCasasComFichas[i] = Integer.valueOf(savedValue);
			}
			
			// salva as posicoes dos peoes
			posicoesDosPeoes = new int[qtPeoes];
			for(int i = 0; i < qtPeoes; i++) {
				savedValue = file.readLine();
				posicoesDosPeoes[i] = Integer.valueOf(savedValue);
			}
			
			// salva as posicoes dos peoes
			inicioDeCadaPeao = new int[qtPeoes];
			for(int i = 0; i < qtPeoes; i++) {
				savedValue = file.readLine();
				inicioDeCadaPeao[i] = Integer.valueOf(savedValue);
			}
						
			// salva as posicoes dos peoes
			fimDeCadaPeao = new int[qtPeoes];
			for(int i = 0; i < qtPeoes; i++) {
				savedValue = file.readLine();
				fimDeCadaPeao[i] = Integer.valueOf(savedValue);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
