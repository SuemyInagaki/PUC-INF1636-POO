package Interfaces;

import Interfaces.ObservadorIF;

public interface ObservadoIF {
//	void add (ObservadorIF o);
	public void addObserver(ObservadorIF o);
	public void removeObserver(ObservadorIF o);
	public Object get();
}
