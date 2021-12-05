package View;

import javax.swing.*;

import java.awt.*;

public class FRLatitude extends JFrame{
	public final int LARG_DEFAULT=1200;
	public final int ALT_DEFAULT=700;
	private CardLayout cardLayout;
	private PNStart initScreen;
	
	public FRLatitude() 
	{
		//super();
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Latitude 90");
		
		Container contentPane = getContentPane();
		
		cardLayout = new CardLayout();
		initScreen = new PNStart(contentPane, cardLayout);		
		
		contentPane.setLayout(cardLayout);
		contentPane.add(initScreen, "initScreen");
	}
	
	
}
