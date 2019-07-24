import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ConfirmPage extends JPanel
{
	private CineplexName cName;
	private ButtonPanel pButton;
	
	private confirmPanel pConfirm;
	private BufferedImage img;
	
	
	ConfirmPage()
	{
		setSize(1366,768);
		setLayout(null);
		
		initialization();
		addComponent();
	}

	private void initialization() 
	{
		cName	    = 	new CineplexName();
		pButton 	=	new ButtonPanel();
		pConfirm    =   confirmPanel.getInstance();
		
	}

	private void addComponent() 
	{
		
		cName.setBounds(100,5,1166,120);
		pButton.setBounds(100,125,1166,50);
		pConfirm.setBounds(100,180,1166,500);
		
		
		try {
			img = ImageIO.read(getClass().getResource("/images/confirm.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(pConfirm);
		
		add(cName);
		add(pButton);
		


		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	public ButtonPanel getBtnPanel() {
		return pButton;
	}

}
