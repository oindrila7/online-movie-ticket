import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmployeePage extends JPanel 
{
	
	private CineplexName cName;
	private TicketSellngPanel pTicket;
	private BufferedImage img;
	
	EmployeePage() 
	{
		setSize(1366,786);
		setLayout(null);
		
		initialization();
		addComponent();
		addActionToButton();
		
	}


	private void addActionToButton() {
		// TODO Auto-generated method stub
		
	
		
		pTicket.getBtnConfirm().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		
	}


	private void initialization() 
	{
		cName = new CineplexName();
		pTicket = new TicketSellngPanel();
		
		
	}


	private void addComponent() 
	{
		
		cName.setBounds(100,5,1166,120);
		
		pTicket.setBounds(233,130,900,550);
		
		try {
			img = ImageIO.read(getClass().getResource("/images/empPage.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		add(cName);
		add(pTicket);
	}
		
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	public TicketSellngPanel getTicketPanel()
	{
		return pTicket;
	}


}
