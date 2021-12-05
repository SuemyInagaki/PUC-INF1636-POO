package View;


import javax.imageio.ImageIO;
import javax.swing.*;

import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;
import Model.APIModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;


public class PNLatitude extends JPanel implements MouseListener, ObservadorIF, ActionListener{
	private SearchFile saveWindow;
	private JPopupMenu popup;
	private CardLayout switchToGame;
	private Container switchSource;
	private int iClick,jClick;
	private APIModel apiModel;
	Image imageTabuleiro;
	int numJogadores;
	int jogadorDaVez = 0;
	int peaoSelecionado = -1;
	private Object lob[];
	private ObservadoIF obs;
	private int[][] casasComPeoes;
	private int[][] vizinhos;
	private int[]dadosRodados;
	private JButton rolarBtn, FimBtn, rolarColoridoBtn;
	private int[] pontuacao;
	private int[] casaSelecionada;
	private int[] qtPeaoCadaJogador;
	private int[] corDosPeoes;
	int movimentosRestantes;
	int qtPeoes;
	boolean mostraBotaoFim = false;
	private String textVencedor = "";
	private boolean fimDeJogo;
	private int vencedor;
	private String textoMovimentoRestante = "";
	private int indiceDadoColorido;
	private boolean temFicha = false;
	private int indiceCarta = -1;
	private int cartaImplementada = -1;
	private String textoJogadorDaVez = "Vez do Jogador 1";
	private int[]indiceDasCasasComFichas = null;
	private int[]posicoesDosPeoes = null;
	private int[] inicioDeCadaPeao = null;
	private int[] fimDeCadaPeao = null;
	String[] nomesDados = new String[]{"images/Dado1.png", "images/Dado2.png", "images/Dado3.png", "images/Dado4.png", "images/Dado5.png", "images/Dado6.png"};
	String[] nomesCartas = new String[]{"images/Cartas-png/C01.png", "images/Cartas-png/C02.png", "images/Cartas-png/C03.png", "images/Cartas-png/C04.png", "images/Cartas-png/C05.png", "images/Cartas-png/C06.png", "images/Cartas-png/C07.png", "images/Cartas-png/C08.png", "images/Cartas-png/C09.png", "images/Cartas-png/C10.png", "images/Cartas-png/C11.png", "images/Cartas-png/C12.png", "images/Cartas-png/C13.png", "images/Cartas-png/C14.png", "images/Cartas-png/C15.png", "images/Cartas-png/C16.png", "images/Cartas-png/C17.png", "images/Cartas-png/C18.png"};
	public PNLatitude(Container content, CardLayout cardLay, String filePath) {
		
		switchSource = content;
		switchToGame = cardLay;
		imageTabuleiro = new ImageIcon("images/Latitude90-Tabuleiro.png").getImage();
		this.setPreferredSize(new Dimension(600, 600));
		this.setLayout(null);
		this.addMouseListener(this);
		apiModel = APIModel.getApiModel();
		this.casasComPeoes =apiModel.novoJogo(filePath);
		this.numJogadores = apiModel.getNumJogadores();
		apiModel.register(this);
		dadosRodados = apiModel.rodaDados();
		dizJogadorDaVez();
		mostraBotaoRolar();
		add(rolarBtn);
		
		FimBtn = new JButton("Fim");
		FimBtn.setBounds(900, 600, 100, 40);
		FimBtn.addActionListener(this);
		FimBtn.setActionCommand("fim");
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		g2d.drawImage(imageTabuleiro, 150, 0, null);
		if(this.temFicha) {
			Image carta = new ImageIcon(this.nomesCartas[this.indiceCarta]).getImage();
			System.out.printf("%s", this.nomesCartas[this.indiceCarta]);
			g2d.drawImage(carta, 900, 300, null);
			System.out.println("TEM FICHA");
			System.out.printf("%d\n", this.indiceCarta);
		}
		if(this.casasComPeoes != null && this.qtPeoes > 0 && this.qtPeaoCadaJogador != null) {
			for(int i = 0; i < this.qtPeoes; i++) {
				if(corDosPeoes[i] == 0) {
					g2d.setColor(Color.RED);
				}
				else if(corDosPeoes[i] == 1) {
					g2d.setColor(Color.YELLOW);
				}
				else if(corDosPeoes[i] == 2) {
					g2d.setColor(Color.GREEN);
				}
				else {
					g2d.setColor(Color.BLACK);
				}
				if(peaoSelecionado == i) {
					g2d.setColor(Color.CYAN);
				}
				g2d.fillOval(casasComPeoes[i][0]-10, casasComPeoes[i][1]-10, 20, 20);
			}
		}
		if(this.dadosRodados != null) {
			Image dado1 = new ImageIcon(this.nomesDados[this.dadosRodados[0]-1]).getImage();
			Image dado2 = new ImageIcon(this.nomesDados[this.dadosRodados[1]-1]).getImage();
			g2d.drawImage(dado1, 20, 60, null);
			g2d.drawImage(dado2, 20, 160, null);
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Arial", Font.BOLD, 25));
			if(this.jogadorDaVez == 0) {
				textoJogadorDaVez = "Jogador 1";
			}else if(this.jogadorDaVez == 1) {
				textoJogadorDaVez = "Jogador 2";
			}else if(this.jogadorDaVez == 2) {
				textoJogadorDaVez = "Jogador 3";
			}else if(this.jogadorDaVez == 3) {
				textoJogadorDaVez = "Jogador 4";
			}
			g2d.drawString(textoJogadorDaVez, 10, 40);
			textoMovimentoRestante = String.valueOf(movimentosRestantes);
			g2d.drawString(textoMovimentoRestante, 70, 340);
			g2d.drawString("Pontuação", 900, 40);
			String t = "";
			for(int j = 0; j < this.numJogadores; j++) {
				t = "Jogador " + String.valueOf(j+1) + ": " + String.valueOf(this.pontuacao[j]);
				g2d.drawString(t, 900, 40 + 40*(j+1));
			}
			g2d.drawString(textVencedor, 900, 600);
			Color[] cores = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLACK, Color.BLUE, Color.CYAN};
			if(indiceDadoColorido != -1) {
				g2d.setColor(cores[indiceDadoColorido]);
				g2d.fillRect(20, 500, 100, 100);
			}
		}
		
		if(this.vizinhos!= null) {
			for(int i = 0; i < this.vizinhos.length; i++) {
				g2d.setColor(Color.WHITE);
				g2d.fillOval(vizinhos[i][0]-10, vizinhos[i][1]-10, 20, 20);
			}
		}
		if(this.casaSelecionada != null) {
			g2d.setColor(Color.WHITE);
			g2d.fillOval(casaSelecionada[0]-10, casaSelecionada[1]-10, 20, 20);
		}
		
	}
	
	public void mostraBotaoRolar() {
		if(this.dadosRodados != null) {
			rolarBtn = new JButton("Rolar");
			rolarBtn.setBounds(20, 270, 100, 40);
			rolarBtn.addActionListener(this);
			rolarBtn.setActionCommand("rolar");
		}
		
	}
	public void mostraBotaoRolarDadoColorido() {
		if(this.indiceDadoColorido != -1) {
			rolarColoridoBtn = new JButton("Rolar");
			rolarColoridoBtn.setBounds(20, 580, 100, 40);
			rolarColoridoBtn.addActionListener(this);
			rolarColoridoBtn.setActionCommand("rolarColorido");
		}
		
	}
	

	public void dizJogadorDaVez() {
		if(this.jogadorDaVez == 0) {
			textoJogadorDaVez = "Jogador 1";
		}else if(this.jogadorDaVez == 1) {
			textoJogadorDaVez = "Jogador 2";
		}else if(this.jogadorDaVez == 2) {
			textoJogadorDaVez = "Jogador 3";
		}else if(this.jogadorDaVez == 3) {
			textoJogadorDaVez = "Jogador 4";
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		final Point p = e.getPoint();
		int x = (int) p.getX();
		int y = (int) p.getY();
		apiModel.verificaClique(x, y);
//		apiModel.verificaSeSelecionouVizinho(x, y);
		
//		if(e.getButton() == MouseEvent.BUTTON1) {
//			final Point p = e.getPoint();
//			int x = (int) p.getX();
//			int y = (int) p.getY();
//			System.out.println("CYAN");
//			System.out.printf("%d %d\n", x, y);
//			apiModel.verificaSeSelecionouPeao(x, y);
////			if(verificaSeEhNoCirculo() == true) {
////				
////			}else {
////				
////			}
//		}
		if (e.getButton() == MouseEvent.BUTTON3) { // right click
			char[][] cl = new char[1][1];
			boolean[] bl = new boolean[1];
			int[] il = new int[1];
			Vector<String> vs = new Vector<>();
			saveWindow = new SearchFile("Salvando jogo");
			saveWindow.saveMatchFile(this, numJogadores, casasComPeoes, jogadorDaVez, dadosRodados, 
					movimentosRestantes, pontuacao, qtPeaoCadaJogador, corDosPeoes, fimDeJogo, vencedor, indiceDasCasasComFichas, posicoesDosPeoes, inicioDeCadaPeao, fimDeCadaPeao);
			
		}
	}
	
	
	
	public void notify(ObservadoIF o) {
		obs=o;
		lob=(Object []) obs.get();
		numJogadores=(int) lob[0];
		casasComPeoes=(int[][]) lob[1];
		jogadorDaVez=(int) lob[2];
		dadosRodados=(int[])lob[3];
		peaoSelecionado = (int)lob[4];
		vizinhos = (int[][])lob[5];
		casaSelecionada = (int[])lob[6];
		movimentosRestantes=(int)lob[7];
		pontuacao=(int[])lob[8];
		qtPeaoCadaJogador=(int[])lob[9];
		qtPeoes=(int)lob[10];
		corDosPeoes=(int[])lob[11];
		fimDeJogo=(boolean)lob[12];
		vencedor=(int)lob[13];
		temFicha=(boolean)lob[14];
		indiceCarta=(int)lob[15];
		cartaImplementada=(int)lob[16];
		indiceDadoColorido=(int)lob[17];
		indiceDasCasasComFichas=(int[])lob[18];
		posicoesDosPeoes=(int[])lob[19];
		inicioDeCadaPeao=(int[])lob[20];
		fimDeCadaPeao=(int[])lob[21];
		for(int i = 0; i < this.numJogadores; i++) {
			if(qtPeaoCadaJogador[i] == 0) {
				fimDeJogo = true;
				if(vencedor == -1) {
					vencedor = i;
				}
				break;
			}
		}
		if(this.indiceDadoColorido != -1) {
//			add(rolarColoridoBtn);
		}
		if(fimDeJogo) {
			textVencedor = "Jogador " + String.valueOf(vencedor+1) + " venceu!";
			mostraBotaoFim = true;
			add(FimBtn);
		}
//		cor=(boolean) lob[1];
//		xm=(boolean) lob[2];
//		cong=(boolean) lob[3];
//		tab=(int[][]) lob[4];
		
//		String msg="";
//			
//		// xeque-mate ou congelamento
//		if (cong)
//			msg="Empate";
//		else if (xm)
//			if(cor)
//				msg="Xeque-mate sobre as brancas. Pretas ganharam.";
//			else
//				msg="Xeque-mate sobre as pretas. Brancas ganharam.";
//		
		repaint();
		
//		if(msg!="") {
//			repaint();
//			JOptionPane.showMessageDialog(this,msg);
//			
//			PNStart initScreen = new PNStart(switchSource, switchToGame);		
//			switchSource.setLayout(switchToGame);
//			switchSource.add(initScreen, "initScreen");
//			switchToGame.show(switchSource, "initScreen");
//			
//		}
		
	}
	public void mouseEntered(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
        
		if (action.equals("rolar")) {
			dadosRodados = apiModel.rodaDados();
			repaint();
		} else if(action.equals("fim")) {
			PNStart pnStart = new PNStart(switchSource, switchToGame);
        	switchSource.add(pnStart, "chessBoard");
        	switchToGame.show(switchSource, "chessBoard");
		}else if(action.equals("rolarColorido")) {
			indiceDadoColorido = apiModel.rodaDadoColorido();
			
			repaint();
		}
	}
}
