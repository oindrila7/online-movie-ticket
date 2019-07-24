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
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UserLogIn extends JPanel 
{

	private CineplexName cName;          				//to show cineplex name on top of the page
	
	private BufferedImage img; 
	
	private ButtonPanel pButton;         				//home,schedule,movie list,login button panel
	
	
	private JButton btnLogIn;	
	
	
	private LogInapnel pLogin;
	
	UserLogIn()
	{
		
		setSize(1366,768);
		setLayout(null);
		//setIcon(new ImageIcon(this.getClass().getResource("/images/HomeBack.jpg"))); 
		
		
		
		initialization();
		addComponentTo();
		addActionListenToButton();
		
	}

	private void initialization() 
	{
		cName = new CineplexName();
		
		
		pButton= new ButtonPanel();
		
		
		btnLogIn = new JButton("LOG IN");
		
		pLogin= new LogInapnel();
		
		
		
	}

	
	private void addComponentTo() {
		cName.setBounds(100,5,1166,120);
		
		pButton.setBounds(100,130,1166,50);
		
		pLogin.setBounds(483,300,400,220);
		
		
		btnLogIn.setBounds(583, 520, 200, 40);
		btnLogIn.setBackground(new Color(205,92,92));
		btnLogIn.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		try {
			img = ImageIO.read(getClass().getResource("/images/Log.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		add(cName);
		add(pButton);
		add(pLogin);
		add(btnLogIn);
		
		
		
	}
	
	
	public JButton getBtnLogin()
	{
		return btnLogIn;
	}
	
	public ButtonPanel getBtnPanel()
	{
		return pButton;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	public LogInapnel getLoginPanel()
	{
		return pLogin;
	}
	
	
	private void addActionListenToButton() {
	
		
		btnLogIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
