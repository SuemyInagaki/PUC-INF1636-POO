package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;
//implements ObservadoIF
class DadoColorido{
	private static DadoColorido inst = null;
	private Random geraValor = new Random();
	private List<ObservadorIF> lista = new ArrayList<ObservadorIF>();
	private int valor = 0;
	private DadoColorido() {}
	
	static DadoColorido getDado() {
		if(inst == null) {
			inst = new DadoColorido();
		}
		return inst;
	}
	
	int getValor() {
		if(inst == null) {
			getDado();
		}
		valor = geraValor.nextInt(6) + 1;
		return valor;
	}
}
