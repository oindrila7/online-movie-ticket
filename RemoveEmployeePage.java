import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RemoveEmployeePage extends JPanel
{
	
	private CineplexName cName;
	private RemoveEmployeePanel pRemove;
	
	private BufferedImage img;
	
	
	RemoveEmployeePage()
	{
		
		setSize(1366,768);
		setLayout(null);
		
		initialization();
		addComponent();
		
		
	}


	


	private void initialization() {
		cName = new CineplexName();
		pRemove = new RemoveEmployeePanel();
		
	}
	private void addComponent() {
		
		cName.setBounds(100, 5, 1166, 120);
		pRemove.setBounds(283, 130, 800, 500);
		
		try {
			img = ImageIO.read(getClass().getResource("/images/removeEmp.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		add(cName);
		add(pRemove);
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}
