import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class NewUserPage extends JPanel
{
	
	private ButtonPanel pButton;         		 //home,schedule,movie list,login button panel
	private CineplexName cName;          		 //to show cineplex name on top of the page
	private NewUserForm nUser;                   //new user form where user will input their info
	
	private BufferedImage img;
	//JLabel newUser = new JLabel();
	
	
	public NewUserPage() 
	{
		//setIcon(new ImageIcon("G:/javacode/workspace/MovieTheaterManagementSystem/src/images/HomeBack.jpg")); 
		//setIcon(new ImageIcon("G:/OneDrive/Java Project/Eclipse Workspace/java project test/src/images/batman-v-superman-dawn-of-just.jpg")); 
		
		setLayout(null);
		initialization();
		addComponent();	
	}




	private void initialization() 
	{
		pButton= new ButtonPanel();
		cName= new CineplexName();
		nUser = new NewUserForm();
		
	}




	private void addComponent() 
	{
		cName.setBounds(100,5,1166,120);
		pButton.setBounds(100,130,1166,50);
		
		nUser.setBounds(260,180,900,500);
		
		setBounds(0, 0, 1366, 768);
		
		//newUser.setIcon(new ImageIcon("G:/OneDrive/Java Project/Eclipse Workspace/java project test/src/images/batman-v-superman-dawn-of-just.jpg")); 
		
		try {
			img = ImageIO.read(getClass().getResource("/images/signUp.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		add(cName);
		add(pButton);
		add(nUser);
		
		//add(newUser);
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	public ButtonPanel getBtnPanel()
	{
		return pButton;
	}
	
	public NewUserForm getFormPanel()
	{
		return nUser;
	}
	
	
	
}
