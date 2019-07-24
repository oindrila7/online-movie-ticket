/*admin log in page
 * 
 * 
 * 
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Statement;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class AdminLogIn extends JPanel
{
	private CineplexName cName;          				//to show cineplex name on top of the page
	
	private BufferedImage img;
	
	private ButtonPanel pButton;         				//home,schedule,movie list,login button panel
	
	private JButton btnLogInAdmin;	
	
	private LogInapnel pLogin;
	
	AdminLogIn()
	{
		
		setSize(1366,786);
		setLayout(null);
		//setIcon(new ImageIcon(this.getClass().getResource("/images/HomeBack.jpg"))); 
		
		
		
		initialization();
		addComponentTo();
		//addActionListenToButton();
		
	}

	private void initialization() 
	{
		cName = new CineplexName();
		
		
		pButton= new ButtonPanel();
		
		
		btnLogInAdmin = new JButton("LOG IN");
		
		pLogin= new LogInapnel();
		
		
		
	}

	
	private void addComponentTo() {
		cName.setBounds(100,5,1166,120);
		
		pButton.setBounds(100,130,1166,50);
		
		pLogin.setBounds(483,300,400,220);
		
		
		btnLogInAdmin.setBounds(583, 520, 200, 40);
		btnLogInAdmin.setBackground(new Color(205,92,92));
		btnLogInAdmin.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		
		try {
			img = ImageIO.read(getClass().getResource("/images/Log.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		add(cName);
		add(pButton);
		add(pLogin);
		add(btnLogInAdmin);
		
		
		
		
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
	
	public LogInapnel getLoginPanel()
	{
		return pLogin;
	}
	
	public JButton getBtnLogInAdmin()
	{
		return btnLogInAdmin;
	}
	
		
		

}
