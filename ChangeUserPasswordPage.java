import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class ChangeUserPasswordPage extends JLabel{
	
	CineplexName cName;
	ButtonPanel pButton;
	ChangeUserPasswordPanel pchange;
	BufferedImage img;
	
	
	public ChangeUserPasswordPage() 
	{
		
		setSize(1366,768);
		setLayout(null);
		initalization();
		addComponent();
		
	}


	private void initalization() 
	{
		
		cName = new CineplexName();
		pButton= new ButtonPanel();
		
		pchange= new ChangeUserPasswordPanel();
		
		
	}


	private void addComponent() {
		cName.setBounds(100,5,1166,120);
		pButton.setBounds(100,1166,128,50);
		pchange.setBounds(400,195,566,400);
		
		try {
			img = ImageIO.read(getClass().getResource("/images/Log.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(cName);
		add(pButton);
		add(pchange);
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}
