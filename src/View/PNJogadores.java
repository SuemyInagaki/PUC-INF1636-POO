package View;


import javax.imageio.ImageIO;
import javax.swing.*;

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


public class PNJogadores extends JPanel implements ActionListener{
	private SearchFile saveWindow;
	private JPopupMenu popup;
	private JButton doisBtn, quatroBtn;
	private Boolean novoJogo;
	private CardLayout switchToGame; // o que é isso?
	private Container switchSource; // o que é isso?
	private PNLatitude tabuleiro;
	private APIModel apiModel;
	
	public PNJogadores(Container content, CardLayout cardLay, String filePath) {
		
		buildDoisBtn();
		buildQuatroBtn();
		switchSource = content;
		switchToGame = cardLay;
		apiModel = APIModel.getApiModel();
		add(doisBtn);
		add(quatroBtn);
		
	}
	
	private void buildDoisBtn() {
		doisBtn = new JButton("2 Jogadores");
		doisBtn.addActionListener(this);
		doisBtn.setActionCommand("2 Jogadores");
	}
	private void buildQuatroBtn() {
		quatroBtn = new JButton("4 Jogadores");
		quatroBtn.addActionListener(this);
		quatroBtn.setActionCommand("4 Jogadores");
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        if (action.equals("2 Jogadores")) {
        	apiModel.setNumJogadores(2);
        	tabuleiro = new PNLatitude(switchSource, switchToGame, null);
        	switchSource.add(tabuleiro, "chessBoard");
        	switchToGame.show(switchSource, "chessBoard");
        }            
        
        else if (action.equals("4 Jogadores")) {
        	apiModel.setNumJogadores(4);
        	tabuleiro = new PNLatitude(switchSource, switchToGame, null);
        	switchSource.add(tabuleiro, "chessBoard");
        	switchToGame.show(switchSource, "chessBoard");
        }        
    }
}
