package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;
//implements ObservadoIF
class Dado{
	private static Dado inst = null;
	private Random geraValor = new Random();
//	private List<ObservadorIF> lista = new ArrayList<ObservadorIF>();
	private int valor = 0;
	private Dado() {}
	
	static Dado getDado() {
		if(inst == null) {
			inst = new Dado();
		}
		return inst;
	}
	
	int geraValor() {
//		ListIterator<ObservadorIF> listai = lista.listIterator();
		valor = geraValor.nextInt(6) + 1;
		return valor;
//		while(listai.hasNext()) {
//			listai.next().notify(this);
//		}
	}
	
//	@Override
//	public void add(ObservadorIF o) {
//		lista.add(o);
//	}
//
//	@Override
//	public int get() {
//		return valor;
//	}
}
