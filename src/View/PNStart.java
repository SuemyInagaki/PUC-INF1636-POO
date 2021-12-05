package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Model.APIModel;


public class PNStart extends JPanel implements ActionListener{
	private JButton novoJogoBtn, jogoSalvoBtn;
	private Boolean novoJogo;
	private CardLayout switchToGame; // o que é isso?
	private Container switchSource; // o que é isso?
	private PNLatitude tabuleiro;
	private PNJogadores pnJogadores;
	private SearchFile janelaJogoSalvo;
	
	
	public PNStart(Container content, CardLayout cardLay) {
			
		buildNovoJogoBtn();
		buildJogoSalvosBtn();
		switchSource = content;
		switchToGame = cardLay;
	
		add(novoJogoBtn);
		add(jogoSalvoBtn);
	}

	private void buildNovoJogoBtn() {
		novoJogoBtn = new JButton("Novo Jogo");
		novoJogoBtn.addActionListener(this);
		novoJogoBtn.setActionCommand("novoJogo");
	}
	private void buildJogoSalvosBtn() {
		jogoSalvoBtn = new JButton("Jogo Salvo");
		jogoSalvoBtn.addActionListener(this);
		jogoSalvoBtn.setActionCommand("jogoSalvo");
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        if (action.equals("novoJogo")) {
        	pnJogadores = new PNJogadores(switchSource, switchToGame, null);
        	switchSource.add(pnJogadores, "chessBoard");
        	switchToGame.show(switchSource, "chessBoard");
        }            
        
        else if (action.equals("jogoSalvo")) {
        	janelaJogoSalvo = new SearchFile("Carregando partida");
        	String filePath = janelaJogoSalvo.getChosenFilePath(this);
        	
        	if (filePath != null) {
        		tabuleiro = new PNLatitude(switchSource, switchToGame, filePath);
            	switchSource.add(tabuleiro, "chessBoard");
            	switchToGame.show(switchSource, "chessBoard");
        	}
        }        
    }
	
}
