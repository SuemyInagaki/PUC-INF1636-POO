package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Casa{
	private int[] vizinhos;
	private Boolean ficha;
	private int qtPeao;
	private int x;
	private int y;
	private static Casa inst[] = null;

	Casa(int qtPeao, int[] viz, int x, int y, Boolean ficha) {
		this.qtPeao = qtPeao;
		this.vizinhos = viz; 
		this.ficha = ficha;
		this.x = x;
		this.y = y;
	}
	
	static Casa[] getCasa(String filename, int[] fichas) {
		if(filename == null) {
			ArrayList<Integer> casasComFichas = new ArrayList<>( Arrays.asList(1, 7, 15, 21, 24, 30, 74, 80, 88, 94, 97, 103)); 
			if(inst == null) {
				inst = new Casa[148];
				Vizinhos v = new Vizinhos();
				int[][] vizinhos = v.getListaVizinhos();
				int[][] coordenadas = v.getCoordenadas();

				for(int i = 0; i < vizinhos.length; i++) {
					if(casasComFichas.contains(i) == true) {
						inst[i] = new Casa(0, vizinhos[i], coordenadas[i][0], coordenadas[i][1], true);
					}else {
						inst[i] = new Casa(0, vizinhos[i], coordenadas[i][0], coordenadas[i][1], false);
					}
				}
			}
		}else {
			if(inst == null) {
				ArrayList<Integer> casasComFichas = new ArrayList<>();
				for(int i = 0; i < fichas.length; i++) {
					casasComFichas.add(fichas[i]);
				}
				inst = new Casa[148];
				Vizinhos v = new Vizinhos();
				int[][] vizinhos = v.getListaVizinhos();
				int[][] coordenadas = v.getCoordenadas();
				for(int i = 0; i < vizinhos.length; i++) {
					if(casasComFichas.contains(i) == true) {
						inst[i] = new Casa(0, vizinhos[i], coordenadas[i][0], coordenadas[i][1], true);
					}else {
						inst[i] = new Casa(0, vizinhos[i], coordenadas[i][0], coordenadas[i][1], false);
					}
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
	public int get() {
		return this.qtPeao;
	}
	
	void setQtPeao(int qt) {
		this.qtPeao = qt;
	}
	
	Boolean getFicha() {
		return this.ficha;
	}
	
	void removeFicha() {
		if(this.ficha == true) {
			this.ficha = false;
		}
	}
	
	
	int[] getVizinhos() {
		return this.vizinhos;
	}
	
	int getX() {
		return this.x;
	}
	
	int getY() {
		return this.y;
	}
}
