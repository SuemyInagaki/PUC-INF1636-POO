package View;

import java.awt.Component;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



public class SearchFile extends JFileChooser {
	
//	private static final long serialVersionUID = 1L;

	public SearchFile(String winTitle) {
		setDialogTitle(winTitle);
		setFileSelectionMode(JFileChooser.FILES_ONLY);
		setFileFilter(new FileNameExtensionFilter("Arquivo de texto (.txt)", "txt"));
		setAcceptAllFileFilterUsed(false);
	}	
	
	public String getChosenFilePath(Component parent) {		
    	int retorno = showOpenDialog(parent);
    	if (retorno == JFileChooser.APPROVE_OPTION) 
    		return this.getSelectedFile().getPath();
    	return null;
	}
	
	public void saveMatchFile(Component parent, 
		int numJogadores, int[][] casas, int jogadorDaVez, int[] dadosRodados, 
		int movimentosRestantes, int[] pontuacao, int[] qtPeaoCadaJogador, int[] corDosPeoes, boolean fimDeJogo, int vencedor, int[] indiceDasCasasComFichas, int[] posicoesDosPeoes, int[] inicioDeCadaPeao, int[] fimDeCadaPeao) {	
		// falta pegar indice das casas com fichas
		// indice das casas com peoes
		int retorno = showSaveDialog(parent);
		if (retorno == JFileChooser.APPROVE_OPTION) {		
			try(FileWriter fw = new FileWriter(getSelectedFile() + ".txt")) {	
				int i;
				fw.write(String.valueOf(numJogadores) + "\n"); // numJogadores
				fw.write(String.valueOf(casas.length) + "\n"); // quantidade de peoes no tab
				for(i = 0; i < casas.length; i++) { // posicao de cada peao
					fw.write(String.valueOf(casas[i][0]) + "\n");
					fw.write(String.valueOf(casas[i][1]) + "\n");
				}
				fw.write(String.valueOf(jogadorDaVez) + "\n");
				fw.write(String.valueOf(dadosRodados[0]) + "\n");
				fw.write(String.valueOf(dadosRodados[1]) + "\n");
				fw.write(String.valueOf(movimentosRestantes) + "\n");
				
				for(i = 0; i < numJogadores; i++) {
					fw.write(String.valueOf(pontuacao[i]) + "\n");
				}
				
				for(i = 0; i < numJogadores; i++) {
					fw.write(String.valueOf(qtPeaoCadaJogador[i]) + "\n");
				}
				for(i = 0; i < corDosPeoes.length; i++) {
					fw.write(String.valueOf(corDosPeoes[i]) + "\n");
				}
				if(fimDeJogo) {
					fw.write("true\n");
					fw.write(String.valueOf(vencedor)+ "\n");
				}else {
					fw.write("false\n");
					fw.write("-1\n");
				}
				
				fw.write(String.valueOf(indiceDasCasasComFichas.length) + "\n");
				for(i = 0; i < indiceDasCasasComFichas.length; i++) {
					fw.write(String.valueOf(indiceDasCasasComFichas[i]) + "\n");
				}
				for(i = 0; i < posicoesDosPeoes.length; i++) {
					fw.write(String.valueOf(posicoesDosPeoes[i]) + "\n");
				}
				for(i = 0; i < inicioDeCadaPeao.length; i++) {
					fw.write(String.valueOf(inicioDeCadaPeao[i]) + "\n");
				}
				for(i = 0; i < fimDeCadaPeao.length; i++) {
					fw.write(String.valueOf(fimDeCadaPeao[i]) + "\n");
				}
			} 
			
			catch (IOException e) {
	            System.out.printf("Error: %s", e.getMessage());
	        }
		}
	}
}
