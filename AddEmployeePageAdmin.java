import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddEmployeePageAdmin extends JPanel 
{
	
	private CineplexName cName;
	private AddEmployeePanel pAddEmployee;
	//JLabel addEmpLabel = new JLabel();
	private BufferedImage img;
	
	
	AddEmployeePageAdmin() 
	{
		setSize(1366,768);
		setLayout(null);
		
		initialization();
		addComponent();
		
		addActionToButton();
		
	}


	


	private void addActionToButton() {
		// TODO Auto-generated method stub
		
		
	}





	private void initialization() 
	{
		cName = new CineplexName();
		pAddEmployee = new AddEmployeePanel();
		
		
		
	}


	private void addComponent() 
	{
		
		cName.setBounds(100,5,1166,120);
		
		pAddEmployee.setBounds(233,130,900,550);
		
		try {
			img = ImageIO.read(getClass().getResource("/images/addEmp.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(cName);
		add(pAddEmployee);
		//add(addEmpLabel);
	}
		
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}
