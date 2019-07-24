import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddShowPageAdmin extends JPanel 
{
	
	private CineplexName cName;
	public AddShowPanel pAddShow;
	
	private BufferedImage img;
	
	
	AddShowPageAdmin() 
	{
		setSize(1366,768);
		setLayout(null);
		
		initialization();
		addComponent();
		
		
		
	}


	


	private void initialization() 
	{
		cName = new CineplexName();
		pAddShow = new AddShowPanel();
		
		
		
	}


	private void addComponent() 
	{
		
		cName.setBounds(100,5,1166,120);
		
		pAddShow.setBounds(233,130,900,550);
		
		try {
			img = ImageIO.read(getClass().getResource("/images/addShow.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(cName);
		add(pAddShow);
	}
		
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	public AddShowPanel getAddShowPanel()
	{
		return pAddShow;
	}

}
