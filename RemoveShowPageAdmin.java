import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RemoveShowPageAdmin extends JPanel 
{
	
	private CineplexName cName;
	private RemoveShowPanel pRemoveShow;
	BufferedImage img;
	
	RemoveShowPageAdmin() 
	{
		setSize(1366,768);
		setLayout(null);
		
		initialization();
		addComponent();
		
	}


	private void initialization() 
	{
		cName = new CineplexName();
		pRemoveShow = new RemoveShowPanel();
		
		
		
	}


	private void addComponent() 
	{
		
		cName.setBounds(100,5,1166,120);
		
		pRemoveShow.setBounds(233,130,900,550);
		
		try {
			img = ImageIO.read(getClass().getResource("/images/removeShow.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(cName);
		add(pRemoveShow);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	public RemoveShowPanel getRemoveShowPanel()
	{
		return pRemoveShow;
	}

}
