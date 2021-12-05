package Controller;

import View.FRLatitude;
import Model.*;
public class Controller {
	 
	
	public static void main(String args[]) {
		String filename = null;
		APIModel api = new APIModel();
		
		api.setNumJogadores(2);
		new FRLatitude().setVisible(true);
		api.novoJogo(filename);
	}
}


