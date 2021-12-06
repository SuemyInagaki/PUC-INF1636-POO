package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;

class Dado{
	private static Dado inst = null;
	private Random geraValor = new Random();
	private int valor = 0;
	private Dado() {}
	
	static Dado getDado() {
		if(inst == null) {
			inst = new Dado();
		}
		return inst;
	}
	
	int geraValor() {
		valor = geraValor.nextInt(6) + 1;
		return valor;
	}
	
}
